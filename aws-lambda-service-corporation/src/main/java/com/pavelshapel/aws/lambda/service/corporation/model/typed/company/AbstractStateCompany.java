package com.pavelshapel.aws.lambda.service.corporation.model.typed.company;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class AbstractStateCompany extends AbstractCompany {
    protected AbstractStateCompany() {
        super(13);
    }
}
