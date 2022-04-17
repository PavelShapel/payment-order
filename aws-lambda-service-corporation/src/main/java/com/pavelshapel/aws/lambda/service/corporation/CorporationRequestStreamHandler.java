package com.pavelshapel.aws.lambda.service.corporation;

import com.pavelshapel.aws.spring.boot.starter.config.AbstractRequestStreamHandler;

public class CorporationRequestStreamHandler extends AbstractRequestStreamHandler {
    protected CorporationRequestStreamHandler() {
        super(AwsLambdaServiceCorporationApplication.class);
    }
}