package com.pavelshapel.aws.lambda.service.corporation.config;

import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.core.spring.boot.starter.api.bean.BeansCollection;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("NullableProblems")
@Component
public class CorporationContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private BeansCollection<Typed<Type>> typedBeansCollection;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<Type> notImplementedTypedBeans = Arrays.stream(Type.values())
                .filter(type -> !typedBeansCollection.getBean(typed -> typed.getType().equals(type)).isPresent())
                .collect(Collectors.toList());
        if (!notImplementedTypedBeans.isEmpty()) {
            throw new EntityNotFoundException(String.format("bean(s) for type(s) %s not found", notImplementedTypedBeans));
        }
    }
}