package com.pavelshapel.service.location;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ServiceLocationApplication implements CommandLineRunner {

    public static void main(String[] args) {
        log.info("starting the application");
        SpringApplication.run(ServiceLocationApplication.class, args);
        log.info("starting the application");
    }

    @Override
    public void run(String... args) {
        log.info("Hello world!");
    }

}
