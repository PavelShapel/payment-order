package com.pavelshapel.aws.lambda.service.corporation.config;

import com.pavelshapel.aws.lambda.service.corporation.repositiory.CorporationDaoRepository;
import com.pavelshapel.aws.spring.boot.starter.config.AbstractDynamoDbConfig;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableDynamoDBRepositories(basePackageClasses = {CorporationDaoRepository.class})
@EnableJpaRepositories(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {CorporationDaoRepository.class})})
public class CorporationDynamoDbConfig extends AbstractDynamoDbConfig {
}
