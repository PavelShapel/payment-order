package com.pavelshapel.aws.lambda.service.corporation.model.typed.company;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.stereotype.Component;

import static com.pavelshapel.aws.lambda.service.corporation.model.Type.OWNER;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Component(SCOPE_PROTOTYPE)
public class Owner extends AbstractOtherCompany {
    protected Owner() {
        super(OWNER);
    }
}
