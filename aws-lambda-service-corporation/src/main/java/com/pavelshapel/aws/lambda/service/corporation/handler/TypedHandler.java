package com.pavelshapel.aws.lambda.service.corporation.handler;

import com.pavelshapel.aws.lambda.service.corporation.model.Type;
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
public class TypedHandler implements Handler {
    JsonConverter jsonConverter;

    @Override
    public Optional<? extends Typed<Type>> getTyped(TypedDto typedDto) {
        return Optional.ofNullable(typedDto)
                .map(Typed::getType)
                .map(Type::valueOf)
                .map(Type::getTargetClass)
                .map(targetClass -> jsonConverter.mapToPojo(typedDto, targetClass));
    }

    @Override
    public Optional<? extends Typed<Type>> getTyped(Type type) {
        return Optional.ofNullable(type)
                .map(Enum::name)
                .map(Object.class::cast)
                .map(typeName -> singletonMap(TYPE_FIELD, typeName))
                .map(TypedDto::new)
                .flatMap(this::getTyped);
    }

    @Override
    public Typed<Type> deserializeTyped(String json) {
        TypedDto typedDto = jsonConverter.jsonToPojo(json, TypedDto.class);
        return getTyped(typedDto).orElse(null);
    }

    @Override
    public String serializeTypedBean(Typed<Type> typed) {
        return jsonConverter.pojoToJson(typed);
    }
}
