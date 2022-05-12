package com.pavelshapel.aws.lambda.service.nbrb.config;

import com.pavelshapel.aws.spring.boot.starter.config.AbstractS3AwsConfiguration;
import com.pavelshapel.aws.spring.boot.starter.properties.AwsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NbrbS3AwsConfiguration extends AbstractS3AwsConfiguration {
    @Autowired
    protected NbrbS3AwsConfiguration(AwsProperties awsProperties) {
        super(awsProperties);
    }
}
