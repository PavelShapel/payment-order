package com.pavelshapel.aws.lambda.service.corporation.config;

import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.core.spring.boot.starter.api.bean.BeansCollection;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;
import com.pavelshapel.core.spring.boot.starter.api.util.StreamUtils;
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

import static java.util.Objects.isNull;

@SuppressWarnings("NullableProblems")
@ComponentProfileNotTest
@Log
public class CorporationContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private BeansCollection<Typed<Type>> typedBeansCollection;
    @Autowired
    private Environment environment;
    @Autowired
    private StreamUtils streamUtils;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        verifyNotImplementedTypedBeans();
        verifyNotImplementedTypes();
        logActiveProfiles();
    }

    private void verifyNotImplementedTypes() {
        Arrays.stream(Type.values())
                .filter(type -> isNull(type.getTypes()))
                .collect(streamUtils.toOptionalList())
                .filter(types -> !types.isEmpty())
                .ifPresent(this::throwNotImplementedGetTypesException);
    }

    private void throwNotImplementedGetTypesException(List<Type> types) {
        String message = String.format("getTypes() not implemented for type(s) [%s]", types);
        throw new NotImplementedException(message);
    }

    private void logActiveProfiles() {
        String message = String.format("currently active profiles [%s]", String.join(",", environment.getActiveProfiles()));
        log.info(message);
    }

    private void verifyNotImplementedTypedBeans() {
        Arrays.stream(Type.values())
                .filter(this::notImplementedBean)
                .collect(streamUtils.toOptionalList())
                .filter(types -> !types.isEmpty())
                .ifPresent(this::throwTypedBeansNotImplementedException);
    }

    private void throwTypedBeansNotImplementedException(List<Type> notImplementedTypedBeans) {
        throw new NotImplementedException(String.format("bean(s) for type(s) %s not implemented", notImplementedTypedBeans));
    }

    private boolean notImplementedBean(Type type) {
        Optional<Typed<Type>> bean = typedBeansCollection.getBean(typed -> typed.getType().equals(type));
        return !bean.isPresent();
    }
}