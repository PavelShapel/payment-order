package com.pavelshapel.multi.threading.task.second.repository;

import com.pavelshapel.multi.threading.task.second.entity.Logs;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogsRepository extends MongoRepository<Logs, String> {
}