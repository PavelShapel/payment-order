package com.pavelshapel.aws.lambda.service.corporation.model.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.pavelshapel.aws.lambda.service.corporation.handler.Handler;
import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CorporationTypedDynamoDBTypeConverter implements DynamoDBTypeConverter<String, Typed<Type>> {
    private static Handler staticTypedHandler;

    @Autowired
    public CorporationTypedDynamoDBTypeConverter(Handler typedBeanFactory) {
        staticTypedHandler = typedBeanFactory;
    }

    @Override
    public String convert(Typed<Type> typed) {
        return staticTypedHandler.serializeTypedBean(typed)
                .orElseThrow(() -> new IllegalArgumentException(typed.toString()));
    }

    @Override
    public Typed<Type> unconvert(String json) {
        return staticTypedHandler.deserializeTyped(json)
                .orElseThrow(() -> new IllegalArgumentException(json));
    }
}