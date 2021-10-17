package com.pavelshapel.nosql.task.first;

import com.pavelshapel.nosql.task.second.repository.listener.CascadeSaveMongoEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceLocationApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceLocationApplication.class, args);
    }
    @Bean
    public CascadeSaveMongoEventListener cascadingMongoEventListener() {
        return new CascadeSaveMongoEventListener();
    }
}
