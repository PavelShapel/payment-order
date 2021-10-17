package com.pavelshapel.multi.threading.task.second.repository;

import com.pavelshapel.multi.threading.task.second.entity.Category;
import com.pavelshapel.multi.threading.task.second.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, Long> {
    List<Task> findByDeadlineLessThanEqual(Date deadline);

    List<Task> findByCategoryEquals(Category category);

    List<Task> findByDescriptionContainingIgnoreCase(String description);
}