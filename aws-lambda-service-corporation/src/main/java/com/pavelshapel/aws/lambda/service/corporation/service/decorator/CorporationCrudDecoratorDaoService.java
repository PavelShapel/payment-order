package com.pavelshapel.aws.lambda.service.corporation.service.decorator;

import com.pavelshapel.aws.lambda.service.corporation.model.Corporation;
import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;
import com.pavelshapel.core.spring.boot.starter.api.service.decorator.Decorator;
import com.pavelshapel.core.spring.boot.starter.impl.service.decorator.AbstractDecoratorSpecificationDaoService;

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

    @Override
    public Corporation update(String id, Corporation corporation) {
        verifyAppropriateType(corporation);
        return super.update(id, corporation);
    }

    private void verifyAppropriateType(Corporation corporation) {
        Optional.ofNullable(corporation)
                .map(Corporation::getTyped)
                .map(Typed::getType)
                .map(type -> getParentAppropriateTypes(corporation).contains(type))
                .filter(Boolean.FALSE::equals)
                .ifPresent(unused -> throwMismatchedTypeException());

    }

    private void throwMismatchedTypeException() {
        throw new IllegalArgumentException("mismatched type");
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