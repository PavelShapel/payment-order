package com.pavelshapel.aws.lambda.service.corporation.model.typed.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.aws.lambda.service.corporation.model.typed.AbstractTyped;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class AbstractCompany extends AbstractTyped {
    @Getter
    @Setter
    private String fullName;
    @Getter
    @Setter
    private String number;
    @JsonIgnore
    private final Integer paymentQueue;

    protected AbstractCompany(Type type, Integer paymentQueue) {
        super(type);
        this.paymentQueue = paymentQueue;
    }

    @JsonProperty
    public Integer getPaymentQueue() {
        return paymentQueue;
    }

    @JsonIgnore
    public void setPaymentQueue(Integer paymentQueue) {
        throw new UnsupportedOperationException();
    }
}
