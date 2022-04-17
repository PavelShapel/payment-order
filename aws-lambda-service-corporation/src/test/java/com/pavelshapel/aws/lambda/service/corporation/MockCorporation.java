package com.pavelshapel.aws.lambda.service.corporation;

import com.pavelshapel.aws.lambda.service.corporation.model.Corporation;
import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;

public interface MockCorporation {
    default Corporation getMockCorporation(String id, Corporation parent, Typed<Type> typedType) {
        Corporation corporation = new Corporation();
        corporation.setId(id);
        corporation.setParent(parent);
        corporation.setTyped(typedType);
        return corporation;
    }
}