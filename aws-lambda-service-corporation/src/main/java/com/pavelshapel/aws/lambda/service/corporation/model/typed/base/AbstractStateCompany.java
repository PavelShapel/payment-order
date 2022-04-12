package com.pavelshapel.aws.lambda.service.corporation.model.typed.base;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AbstractStateCompany extends AbstractCompany {
}
