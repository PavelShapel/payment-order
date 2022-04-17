package com.pavelshapel.aws.lambda.service.corporation.provider;

import com.pavelshapel.test.spring.boot.starter.provider.AbstractProvider;

public class TwoStringProvider extends AbstractProvider {
    protected TwoStringProvider() {
        super(String.class, String.class);
    }
}
