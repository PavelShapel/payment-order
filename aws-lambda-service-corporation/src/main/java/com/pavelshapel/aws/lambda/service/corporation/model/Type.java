package com.pavelshapel.aws.lambda.service.corporation.model;

import lombok.Getter;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public enum Type {
    OWNER,
    BANKS,
    CUSTOMERS;

    static {
        OWNER.types = asList(BANKS, CUSTOMERS);
        BANKS.types = emptyList();
        CUSTOMERS.types = emptyList();
    }

    @Getter
    private List<Type> types;
}
