package com.pavelshapel.cloud.provider.web;

import com.pavelshapel.cloud.api.Greetingable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ProviderRestController implements Greetingable {

    @Value("${" + GREETING + ":" + GREETING + "}")
    private String greetingFromConsul;


    @Override
    public String getGreeting() {
        return greetingFromConsul;
    }
}