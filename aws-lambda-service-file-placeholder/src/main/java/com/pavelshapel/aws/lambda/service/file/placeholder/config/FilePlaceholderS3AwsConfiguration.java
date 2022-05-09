package com.pavelshapel.aws.lambda.service.file.placeholder.config;

import com.pavelshapel.aws.spring.boot.starter.config.AbstractS3AwsConfiguration;
import com.pavelshapel.aws.spring.boot.starter.properties.AwsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilePlaceholderS3AwsConfiguration extends AbstractS3AwsConfiguration {
    @Autowired
    protected FilePlaceholderS3AwsConfiguration(AwsProperties awsProperties) {
        super(awsProperties);
    }
}
