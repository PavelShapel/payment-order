package com.pavelshapel.aws.lambda.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T, ID extends Serializable> {
    private final DynamoDBMapper dynamoDBMapper;
    private final Class<T> entityClass;

    protected AbstractRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    public void save(T t) {
        dynamoDBMapper.save(t);
    }

    public Optional<T> findOne(ID id) {
        return Optional.ofNullable(dynamoDBMapper.load(entityClass, id));
    }

    public List<T> findAll() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(entityClass, scanExpression);
    }
}