package com.pavelshapel.aws.lambda.service.corporation.model.typed.company;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class Bank extends AbstractOtherCompany {
    private String swift;
}
