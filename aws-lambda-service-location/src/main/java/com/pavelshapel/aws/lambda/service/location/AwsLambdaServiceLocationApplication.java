package com.pavelshapel.aws.lambda.service.location;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication(exclude = {
        DataSourceTransactionManagerAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
@Transactional
public class AwsLambdaServiceLocationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AwsLambdaServiceLocationApplication.class, args);
    }
}
