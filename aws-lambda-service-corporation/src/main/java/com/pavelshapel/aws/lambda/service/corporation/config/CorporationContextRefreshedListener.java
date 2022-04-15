package com.pavelshapel.aws.lambda.service.corporation.config;

import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.core.spring.boot.starter.api.bean.BeansCollection;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;
import com.pavelshapel.core.spring.boot.starter.impl.bean.ComponentProfileNotTest;
import lombok.extern.java.Log;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("NullableProblems")
@ComponentProfileNotTest
@Log
public class CorporationContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private BeansCollection<Typed<Type>> typedBeansCollection;
    @Autowired
    private Environment environment;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        verifyNotImplementedTypedBeans();
        logActiveProfiles();
    }

    private void logActiveProfiles() {
        String message = String.format("currently active profiles [%s]", String.join(",", environment.getActiveProfiles()));
        log.info(message);
    }

    private void verifyNotImplementedTypedBeans() {
        List<Type> notImplementedTypedBeans = Arrays.stream(Type.values())
                .filter(this::notImplementedBean)
                .collect(Collectors.toList());
        if (!notImplementedTypedBeans.isEmpty()) {
            throw new NotImplementedException(String.format("bean(s) for type(s) %s not implemented", notImplementedTypedBeans));
        }
    }

    private boolean notImplementedBean(Type type) {
        Optional<Typed<Type>> bean = typedBeansCollection.getBean(typed -> typed.getType().equals(type));
        return !bean.isPresent();
    }
}