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

@Component
@NoArgsConstructor
public class CorporationTypedDynamoDBTypeConverter extends AbstractFromDtoConverter implements DynamoDBTypeConverter<String, Typed<Type>> {
    private static BeansCollection<Typed<Type>> staticTypedBeansCollection;
    private static JsonConverter staticJacksonJsonConverter;

    @Autowired
    public CorporationTypedDynamoDBTypeConverter(BeansCollection<Typed<Type>> typedBeansCollection, JsonConverter jacksonJsonConverter) {
        staticTypedBeansCollection = typedBeansCollection;
        staticJacksonJsonConverter = jacksonJsonConverter;
    }

    @Override
    public String convert(Typed<Type> typed) {
        return staticJacksonJsonConverter.pojoToJson(typed)
                .orElseThrow(() -> new IllegalArgumentException(typed.toString()));
    }

    @Override
    public Typed<Type> unconvert(String json) {
        return staticJacksonJsonConverter.jsonToPojo(json, TypedDto.class)
                .flatMap(typedDto -> getMatchedTyped(typedDto, staticJacksonJsonConverter, staticTypedBeansCollection))
                .orElseThrow(() -> new IllegalArgumentException(json));
    }
}