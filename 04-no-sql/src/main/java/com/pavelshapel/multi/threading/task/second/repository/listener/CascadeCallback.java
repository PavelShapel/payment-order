package com.pavelshapel.multi.threading.task.second.repository.listener;

import lombok.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Collection;

import static java.util.Objects.nonNull;

@Value
public class CascadeCallback implements ReflectionUtils.FieldCallback {
    Object source;
    MongoOperations mongoOperations;

    @Override
    public void doWith(final Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);
        if (field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(CascadeSave.class)) {
            Object fieldValue = field.get(source);
            if (nonNull(fieldValue)) {
                FieldCallback callback = new FieldCallback();
                ReflectionUtils.doWithFields(fieldValue.getClass(), callback);
                saveFieldValue(fieldValue);
            }
        }
    }

    private void saveFieldValue(Object fieldValue) {
        if (fieldValue instanceof Collection) {
            ((Collection<?>) fieldValue).forEach(mongoOperations::save);
        } else {
            mongoOperations.save(fieldValue);
        }
    }
}