package com.pavelshapel.nosql.task.second.repository;

import com.pavelshapel.nosql.task.second.entity.Logs;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogsRepository extends MongoRepository<Logs, String> {
}