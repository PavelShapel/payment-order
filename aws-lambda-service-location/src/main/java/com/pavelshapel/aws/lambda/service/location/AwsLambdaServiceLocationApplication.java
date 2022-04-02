package com.pavelshapel.aws.lambda.service.location;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AwsLambdaServiceLocationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AwsLambdaServiceLocationApplication.class, args);
    }
}
