package com.pavelshapel.cloud.consumer.web;

import com.pavelshapel.cloud.api.Greetingable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerRestController {

    @Autowired
    private Greetingable greetingable;

    @GetMapping("test")
    public String test() {
        return greetingable.getGreeting();
    }

}