package com.pavelshapel.nosql.task.second.service;

import com.pavelshapel.test.spring.boot.starter.provider.AbstractProvider;

public class OneStringProvider extends AbstractProvider {
    protected OneStringProvider() {
        super(String.class);
    }
}