package com.pavelshapel.aws.lambda.service.corporation.model.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.core.spring.boot.starter.api.bean.BeansCollection;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;
import com.pavelshapel.core.spring.boot.starter.impl.model.TypedDto;
import com.pavelshapel.json.spring.boot.starter.converter.JsonConverter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@NoArgsConstructor
public class CorporationTypedDynamoDBTypeConverter implements DynamoDBTypeConverter<String, Typed<Type>> {
    private static BeansCollection<Typed<Type>> staticTypedBeansCollection;
    private static JsonConverter staticJacksonJsonConverter;

    @Autowired
    public CorporationTypedDynamoDBTypeConverter(BeansCollection<Typed<Type>> typedBeansCollection, JsonConverter jacksonJsonConverter) {
        staticTypedBeansCollection = typedBeansCollection;
        staticJacksonJsonConverter = jacksonJsonConverter;
    }

    @Override
    public String convert(Typed<Type> typeTyped) {
        return staticJacksonJsonConverter.pojoToJson(typeTyped).orElse(null);
    }

    @Override
    public Typed<Type> unconvert(String json) {
        return getTyped(staticJacksonJsonConverter.jsonToPojo(json, TypedDto.class).orElse(null));
    }

    private Typed<Type> getTyped(Typed<String> typed) {
        return Optional.ofNullable(typed)
                .map(Typed::getType)
                .map(Type::valueOf)
                .map(this::getTypedClass)
                .flatMap(targetClass -> staticJacksonJsonConverter.mapToPojo(((TypedDto) typed), targetClass))
                .orElse(null);
    }

    private Class<Typed<Type>> getTypedClass(Type type) {
        return (Class<Typed<Type>>) staticTypedBeansCollection.getBean(typed -> typed.getType().equals(type))
                .map(Object::getClass)
                .orElse(null);
    }
}