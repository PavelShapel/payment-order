package com.pavelshapel.aws.lambda.service.corporation.model.typed;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Component
@Scope(SCOPE_PROTOTYPE)
@Getter
@Setter
public class Account extends AbstractTyped {
    private String number;
    private boolean primary;
}
