package com.pavelshapel.nosql.task.first.repository;

import com.pavelshapel.nosql.task.first.entity.LocationTypeMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationTypeMongoRepository extends MongoRepository<LocationTypeMongo, Long> {
}