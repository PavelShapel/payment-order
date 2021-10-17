package com.pavelshapel.multi.threading.task.second;

import com.pavelshapel.multi.threading.task.second.repository.listener.CascadeSaveMongoEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecondApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SecondApplicationRunner.class, args);
    }

    @Bean
    public CascadeSaveMongoEventListener cascadingMongoEventListener() {
        return new CascadeSaveMongoEventListener();
    }
}
