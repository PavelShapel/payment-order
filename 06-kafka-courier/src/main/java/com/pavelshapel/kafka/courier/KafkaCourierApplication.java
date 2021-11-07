package com.pavelshapel.kafka.courier;

import com.pavelshapel.jpa.spring.boot.starter.JpaAuditingConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, JpaAuditingConfiguration.class})
public class KafkaCourierApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaCourierApplication.class, args);
    }
}