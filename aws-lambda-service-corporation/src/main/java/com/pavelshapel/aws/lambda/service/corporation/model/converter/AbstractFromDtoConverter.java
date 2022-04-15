package com.pavelshapel.aws.lambda.service.corporation.model.converter;

import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.core.spring.boot.starter.api.bean.BeansCollection;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;
import com.pavelshapel.core.spring.boot.starter.impl.model.TypedDto;
import com.pavelshapel.json.spring.boot.starter.converter.JsonConverter;

import java.util.Optional;

public abstract class AbstractFromDtoConverter {
    protected Optional<Typed<Type>> getMatchedTyped(TypedDto typed, JsonConverter jacksonJsonConverter, BeansCollection<Typed<Type>> typedBeansCollection) {
        return Optional.ofNullable(typed)
                .map(Typed::getType)
                .map(Type::valueOf)
                .flatMap(type -> getTypedClass(type, typedBeansCollection))
                .flatMap(targetClass -> jacksonJsonConverter.mapToPojo((typed), targetClass));
    }

    private Optional<Class<Typed<Type>>> getTypedClass(Type type, BeansCollection<Typed<Type>> typedBeansCollection) {
        return typedBeansCollection.getBean(typed -> typed.getType().equals(type))
                .map(Typed::getClass)
                .map(targetClass -> (Class<Typed<Type>>) targetClass);
    }
}
