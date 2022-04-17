package com.pavelshapel.aws.lambda.service.corporation.handler;

import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;
import com.pavelshapel.core.spring.boot.starter.impl.bean.AbstractBeansCollection;
import org.springframework.stereotype.Component;

@Component
public class TypedBeansCollection extends AbstractBeansCollection<Typed<Type>> {
}
