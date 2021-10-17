package com.pavelshapel.nosql.task.second.service;

import com.pavelshapel.nosql.task.second.entity.Category;
import com.pavelshapel.nosql.task.second.entity.SubTask;
import com.pavelshapel.nosql.task.second.entity.Task;
import com.pavelshapel.nosql.task.second.repository.TaskRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll() {
        log.info("find all tasks");
        return taskRepository.findAll();
    }

    public void deleteAll() {
        log.info("delete all tasks");
        taskRepository.deleteAll();
    }

    public List<Task> findByDeadlineLessThanEqual(Date deadline) {
        log.info("find overdue tasks [{}}]", deadline);
        return taskRepository.findByDeadlineLessThanEqual(deadline);
    }

    public List<Task> findByCategoryEquals(Category category) {
        log.info("find tasks by category [{}}]", category);
        return taskRepository.findByCategoryEquals(category);
    }

    public List<SubTask> findSubTasksByCategoryEquals(Category category) {
        log.info("find subtasks by category [{}}]", category);
        List<Task> categories = taskRepository.findByCategoryEquals(category);
        return categories.stream()
                .flatMap(task -> task.getSubTasks().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public Task save(Task task) {
        log.info("save task [{}}]", task);
        return taskRepository.save(task);
    }

    public Task findById(Long id) {
        log.info("find task by id [{}]", id);
        return taskRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void deleteById(Long id) {
        log.info("delete task by id [{}]", id);
        taskRepository.deleteById(id);
    }

    public List<Task> findByDescriptionContainingIgnoreCase(String description) {
        log.info("find task by description [{}]", description);
        return taskRepository.findByDescriptionContainingIgnoreCase(description);
    }
}
