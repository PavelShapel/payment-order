package com.pavelshapel.nosql.task.second.repository.listener;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class FieldCallback implements ReflectionUtils.FieldCallback {
    @Getter
    private boolean idFound;

    @Override
    public void doWith(Field field) throws IllegalArgumentException {
        ReflectionUtils.makeAccessible(field);
        if (field.isAnnotationPresent(Id.class)) {
            idFound = true;
        }
    }
}