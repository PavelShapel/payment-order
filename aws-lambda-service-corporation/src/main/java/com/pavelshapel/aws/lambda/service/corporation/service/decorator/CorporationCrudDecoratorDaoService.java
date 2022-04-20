package com.pavelshapel.aws.lambda.service.corporation.service.decorator;

import com.pavelshapel.aws.lambda.service.corporation.model.Corporation;
import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;
import com.pavelshapel.core.spring.boot.starter.impl.service.decorator.AbstractDecoratorSpecificationDaoService;
import com.pavelshapel.core.spring.boot.starter.impl.service.decorator.Decorator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Decorator
public class CorporationCrudDecoratorDaoService extends AbstractDecoratorSpecificationDaoService<String, Corporation> {
    @Override
    public Corporation save(Corporation corporation) {
        verifyAppropriateType(corporation);
        return super.save(corporation);
    }

    private void verifyAppropriateType(Corporation corporation) {
        Optional.ofNullable(corporation)
                .filter(unused -> getCount() > 0)
                .map(Corporation::getTyped)
                .map(Typed::getType)
                .filter(type -> !getParentAppropriateTypes(corporation).contains(type))
                .ifPresent(this::throwMismatchedTypeException);

    }

    private void throwMismatchedTypeException(Type type) {
        throw new IllegalArgumentException(String.format("mismatched type [%s]", type));
    }

    private List<Type> getParentAppropriateTypes(Corporation corporation) {
        return Optional.ofNullable(corporation)
                .map(Corporation::getParent)
                .map(Corporation::getTyped)
                .map(Typed::getType)
                .map(Type::getTypes)
                .orElseGet(Collections::emptyList);
    }
}
