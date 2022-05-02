package com.pavelshapel.aws.lambda.service.corporation.handler;

import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;
import com.pavelshapel.core.spring.boot.starter.impl.model.TypedDto;

import java.util.Optional;

public interface Handler {
    Optional<? extends Typed<Type>> getTyped(TypedDto typedDto);

    Optional<? extends Typed<Type>> getTyped(Type type);

    Typed<Type> deserializeTyped(String json);

    String serializeTypedBean(Typed<Type> typed);
}
