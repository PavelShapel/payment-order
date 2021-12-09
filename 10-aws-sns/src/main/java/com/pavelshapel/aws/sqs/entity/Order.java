package com.pavelshapel.aws.sqs.entity;

import lombok.Data;

@Data
public class Order {
    private String orderType;
    private int value;
    private int count;
}
