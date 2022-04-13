package com.pavelshapel.aws.lambda.service.corporation.model;

import lombok.Getter;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public enum Type {
    OWNER,
    BANKS;

    static {
        OWNER.types = asList(BANKS);
        BANKS.types = emptyList();
    }

    @Getter
    private List<Type> types;
}
