package com.pavelshapel.aws.lambda.service.corporation.model.typed.separator;

import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.aws.lambda.service.corporation.model.typed.AbstractTyped;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class AbstractSeparator extends AbstractTyped {
    protected AbstractSeparator(Type type) {
        super(type);
        setName(EMPTY);
    }
}
