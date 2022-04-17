package com.pavelshapel.aws.lambda.service.corporation.model.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.pavelshapel.aws.lambda.service.corporation.handler.BeanHandler;
import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CorporationTypedDynamoDBTypeConverter implements DynamoDBTypeConverter<String, Typed<Type>> {
    private static BeanHandler staticTypedBeanHandler;

    @Autowired
    public CorporationTypedDynamoDBTypeConverter(BeanHandler typedBeanFactory) {
        staticTypedBeanHandler = typedBeanFactory;
    }

    @Override
    public String convert(Typed<Type> typed) {
        return staticTypedBeanHandler.serializeTypedBean(typed)
                .orElseThrow(() -> new IllegalArgumentException(typed.toString()));
    }

    @Override
    public Typed<Type> unconvert(String json) {
        return staticTypedBeanHandler.deserializeTypedBean(json)
                .orElseThrow(() -> new IllegalArgumentException(json));
    }
}