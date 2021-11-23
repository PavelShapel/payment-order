package com.pavelshapel.aws.lambda.repository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.pavelshapel.aws.lambda.entity.Product;

public class ProductRepository extends AbstractRepository<Product, String> {
    public ProductRepository(DynamoDBMapper dynamoDBMapper) {
        super(dynamoDBMapper);
    }
}