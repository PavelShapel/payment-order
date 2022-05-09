package com.pavelshapel.aws.lambda.service.nbrb.config;

import com.pavelshapel.aws.lambda.service.nbrb.repositiory.NbrbDaoRepository;
import com.pavelshapel.aws.spring.boot.starter.config.AbstractDynamoDbAwsConfiguration;
import com.pavelshapel.aws.spring.boot.starter.properties.AwsProperties;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableDynamoDBRepositories(basePackageClasses = {NbrbDaoRepository.class})
@EnableJpaRepositories(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {NbrbDaoRepository.class})})
public class NbrbDynamoDbAwsConfiguration extends AbstractDynamoDbAwsConfiguration {
    @Autowired
    public NbrbDynamoDbAwsConfiguration(AwsProperties awsProperties) {
        super(awsProperties);
    }
}
