package com.pavelshapel.aws.lambda.service.corporation.model.typed;

import com.pavelshapel.aws.lambda.service.corporation.model.typed.base.AbstractOtherCompany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.pavelshapel.aws.lambda.service.corporation.model.Type.OWNER;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Component(SCOPE_PROTOTYPE)
public class Owner extends AbstractOtherCompany {
    @Override
    @PostConstruct
    protected void postConstruct() {
        setType(OWNER);
    }
}
