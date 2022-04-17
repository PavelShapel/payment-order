package com.pavelshapel.aws.lambda.service.corporation.handler;

import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.core.spring.boot.starter.api.bean.BeansCollection;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;
import com.pavelshapel.core.spring.boot.starter.impl.model.TypedDto;
import com.pavelshapel.json.spring.boot.starter.converter.JsonConverter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.pavelshapel.core.spring.boot.starter.api.model.Typed.TYPE_FIELD;
import static java.util.Collections.singletonMap;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TypedBeanHandler implements BeanHandler {
    JsonConverter jacksonJsonConverter;
    BeansCollection<Typed<Type>> typedBeansCollection;

    @Override
    public Optional<Typed<Type>> getTypedBean(TypedDto typedDto) {
        return Optional.ofNullable(typedDto)
                .map(Typed::getType)
                .map(Type::valueOf)
                .flatMap(type -> getTypedClass(type, typedBeansCollection))
                .flatMap(targetClass -> jacksonJsonConverter.mapToPojo(typedDto, targetClass));
    }

    @Override
    public Optional<Typed<Type>> getTypedBean(Type type) {
        return Optional.ofNullable(type)
                .map(Enum::name)
                .map(Object.class::cast)
                .map(typeName -> singletonMap(TYPE_FIELD, typeName))
                .map(TypedDto::new)
                .flatMap(this::getTypedBean);
    }

    @Override
    public Optional<Typed<Type>> deserializeTypedBean(String json) {
        return jacksonJsonConverter.jsonToPojo(json, TypedDto.class)
                .flatMap(this::getTypedBean);
    }

    @Override
    public Optional<String> serializeTypedBean(Typed<Type> typed) {
        return jacksonJsonConverter.pojoToJson(typed);
    }

    private Optional<Class<Typed<Type>>> getTypedClass(Type type, BeansCollection<Typed<Type>> typedBeansCollection) {
        return typedBeansCollection.getBean(typed -> typed.getType().equals(type))
                .map(Typed::getClass)
                .map(targetClass -> (Class<Typed<Type>>) targetClass);
    }
}
