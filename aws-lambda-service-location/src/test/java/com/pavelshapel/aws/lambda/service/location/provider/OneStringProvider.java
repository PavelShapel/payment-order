package com.pavelshapel.aws.lambda.service.location.provider;

import com.pavelshapel.test.spring.boot.starter.provider.AbstractProvider;

public class OneStringProvider extends AbstractProvider {
    protected OneStringProvider() {
        super(String.class);
    }
}
