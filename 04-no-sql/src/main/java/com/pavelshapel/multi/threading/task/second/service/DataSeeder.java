package com.pavelshapel.multi.threading.task.second.service;

import com.pavelshapel.multi.threading.task.second.entity.Category;
import com.pavelshapel.multi.threading.task.second.entity.SubTask;
import com.pavelshapel.multi.threading.task.second.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class DataSeeder {
    public static final int TASKS_COUNT = Byte.MAX_VALUE;

    @Autowired
    private TaskService taskService;
    @Autowired
    private RandomGenerator randomGenerator;

    public void seeding(){
        LongStream.range(0, TASKS_COUNT)
                .mapToObj(this::createTask)
                .forEach(task -> taskService.save(task));
    }

    public Task createTask(Long id) {
        Task task = new Task();
        task.setId(id);
        task.setName(randomGenerator.getRandomString());
        task.setDateOfCreation(new Date());
        task.setDeadline(randomGenerator.getRandomDate());
        task.setDescription(randomGenerator.getRandomString());
        Category[] categories = Category.values();
        task.setCategory(categories[ThreadLocalRandom.current().nextInt(categories.length)]);
        task.setSubTasks(createSubTasks());
        return task;
    }

    private List<SubTask> createSubTasks() {
        return LongStream.range(1, randomGenerator.getRandomLong())
                .mapToObj(value -> System.currentTimeMillis())
                .map(this::createSubTask)
                .collect(Collectors.toList());
    }

    private SubTask createSubTask(Long id) {
        SubTask subTask = new SubTask();
        subTask.setId(id);
        subTask.setName(randomGenerator.getRandomString());
        return subTask;
    }
}
