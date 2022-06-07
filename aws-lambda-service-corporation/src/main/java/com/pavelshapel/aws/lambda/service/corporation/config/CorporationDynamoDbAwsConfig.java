package com.pavelshapel.aws.lambda.service.corporation.config;

import com.pavelshapel.aws.lambda.service.corporation.repositiory.CorporationDaoRepository;
import com.pavelshapel.aws.spring.boot.starter.config.AbstractDynamoDbAwsConfiguration;
import com.pavelshapel.aws.spring.boot.starter.properties.AwsProperties;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableDynamoDBRepositories(basePackageClasses = {CorporationDaoRepository.class})
@EnableJpaRepositories(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {CorporationDaoRepository.class})})
public class CorporationDynamoDbAwsConfig extends AbstractDynamoDbAwsConfiguration {
    @Autowired
    public CorporationDynamoDbAwsConfig(AwsProperties awsProperties) {
        super(awsProperties);
    }
}
