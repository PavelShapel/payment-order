package com.pavelshapel.aws.lambda.service.location;

import com.pavelshapel.aws.lambda.service.location.web.LocationDaoRestController;
import com.pavelshapel.common.module.dto.service.location.aws.LocationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.function.Supplier;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AwsLambdaServiceLocationApplication {
    @Autowired
    private LocationDaoRestController locationDaoRestController;

    public static void main(String[] args) {
        SpringApplication.run(AwsLambdaServiceLocationApplication.class, args);
    }

    @Bean
    public Supplier<ResponseEntity<List<LocationDto>>> findAll() {
        return () -> locationDaoRestController.findAll();
    }
}
