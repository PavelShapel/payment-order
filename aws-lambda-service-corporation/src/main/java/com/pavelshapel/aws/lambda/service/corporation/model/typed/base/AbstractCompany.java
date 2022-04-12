package com.pavelshapel.aws.lambda.service.corporation.model.typed.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AbstractCompany extends AbstractTyped {
    String fullName;
    String number;
    @JsonIgnore
    Integer paymentQueue;

    @JsonProperty
    public Integer getPaymentQueue() {
        return paymentQueue;
    }

    @JsonIgnore
    public void setPaymentQueue(Integer paymentQueue) {
        this.paymentQueue = paymentQueue;
    }
}
