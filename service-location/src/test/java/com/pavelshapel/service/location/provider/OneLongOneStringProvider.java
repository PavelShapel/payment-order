package com.pavelshapel.service.location.provider;

import com.pavelshapel.test.spring.boot.starter.provider.AbstractProvider;

public class OneLongOneStringProvider extends AbstractProvider {
    protected OneLongOneStringProvider() {
        super(Long.class, String.class);
    }
}
