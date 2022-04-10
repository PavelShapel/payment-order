package com.pavelshapel.aws.lambda.service.corporation.model;

import lombok.Getter;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public enum Type {
    COMPANY,
    LOCATION;

    static {
        COMPANY.types = asList(COMPANY, LOCATION);
        LOCATION.types = singletonList(LOCATION);
    }

    @Getter
    private List<Type> types;
}
