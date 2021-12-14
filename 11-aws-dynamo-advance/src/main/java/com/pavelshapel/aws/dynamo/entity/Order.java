package com.pavelshapel.aws.dynamo.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

@Data
@DynamoDBTable(tableName = "demo_order")
public class Order {
    @DynamoDBHashKey
    private String id;
    @DynamoDBAttribute
    private String orderType;
    @DynamoDBAttribute
    private int value;
    @DynamoDBAttribute
    private int count;
}
