package com.pavelshapel.aws.lambda.service.corporation.handler;

import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;
import com.pavelshapel.core.spring.boot.starter.impl.model.TypedDto;

import java.util.Optional;

public interface BeanHandler {
    Optional<Typed<Type>> getTypedBean(TypedDto typedDto);

    Optional<Typed<Type>> getTypedBean(Type type);

    Optional<Typed<Type>> deserializeTypedBean(String json);

    Optional<String> serializeTypedBean(Typed<Type> typed);
}
