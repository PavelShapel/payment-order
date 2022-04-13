package com.pavelshapel.aws.lambda.service.corporation.model.typed.company;

import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class AbstractStateCompany extends AbstractCompany {
    protected AbstractStateCompany(Type type) {
        super(type, 13);
    }
}
