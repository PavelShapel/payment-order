package com.pavelshapel.aws.lambda.service.corporation.model.typed.separator;

import com.pavelshapel.aws.lambda.service.corporation.model.typed.AbstractTyped;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Component
@Scope(SCOPE_PROTOTYPE)
public class Banks extends AbstractTyped {
}
