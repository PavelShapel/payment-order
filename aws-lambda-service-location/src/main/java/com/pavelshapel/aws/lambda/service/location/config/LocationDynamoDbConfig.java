package com.pavelshapel.aws.lambda.service.location.config;

import com.pavelshapel.aws.lambda.service.location.repository.LocationDaoRepository;
import com.pavelshapel.aws.spring.boot.starter.config.AbstractDynamoDbConfig;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableDynamoDBRepositories(basePackageClasses = {LocationDaoRepository.class})
@EnableJpaRepositories(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {LocationDaoRepository.class})})
public class LocationDynamoDbConfig extends AbstractDynamoDbConfig {
}
