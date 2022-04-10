package com.pavelshapel.aws.lambda.service.corporation;

import com.pavelshapel.aws.spring.boot.starter.config.AbstractRequestStreamHandler;

public class LocationRequestStreamHandler extends AbstractRequestStreamHandler {
    protected LocationRequestStreamHandler() {
        super(AwsLambdaServiceCorporationApplication.class);
    }
}