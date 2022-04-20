package com.pavelshapel.aws.lambda.service.corporation.model;

import com.pavelshapel.aws.lambda.service.corporation.model.typed.Account;
import com.pavelshapel.aws.lambda.service.corporation.model.typed.company.Bank;
import com.pavelshapel.aws.lambda.service.corporation.model.typed.company.Owner;
import com.pavelshapel.aws.lambda.service.corporation.model.typed.separator.Banks;
import com.pavelshapel.aws.lambda.service.corporation.model.typed.separator.Customers;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Getter
public enum Type {
    OWNER(Owner.class),
    ACCOUNT(Account.class),
    BANK(Bank.class),
    BANKS(Banks.class),
    //    CUSTOMER,
    CUSTOMERS(Customers.class);
//    DOCUMENTS,
//    PAYMENT_ORDER;

    static {
        OWNER.types = asList(BANKS, CUSTOMERS);
        ACCOUNT.types = emptyList();
        BANK.types = singletonList(ACCOUNT);
        BANKS.types = singletonList(BANK);
//        CUSTOMER.types = OWNER.getTypes();
        CUSTOMERS.types = emptyList();
//        DOCUMENTS.types = emptyList();
//        PAYMENT_ORDER.types = emptyList();
    }

    private List<Type> types;
    private final Class<? extends Typed<Type>> targetClass;

    Type(Class<? extends Typed<Type>> targetClass) {
        this.targetClass = targetClass;
    }

    public static Type getTypeByClass(Class<? extends Typed<Type>> targetClass) {
        return Arrays.stream(values())
                .filter(type -> targetClass.equals(type.getTargetClass()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(targetClass.getSimpleName()));
    }
}
