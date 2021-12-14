package com.pavelshapel.aws.dynamo.repository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.pavelshapel.aws.dynamo.entity.Order;

public class OrderRepository extends AbstractRepository<Order, String> {
    public OrderRepository(DynamoDBMapper dynamoDBMapper) {
        super(dynamoDBMapper);
    }
}