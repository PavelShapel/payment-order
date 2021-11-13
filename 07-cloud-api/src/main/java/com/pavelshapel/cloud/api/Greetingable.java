package com.pavelshapel.cloud.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cloud-provider")
public interface Greetingable {
    String GREETING = "greeting";

    @GetMapping(GREETING)
    String getGreeting();
}
