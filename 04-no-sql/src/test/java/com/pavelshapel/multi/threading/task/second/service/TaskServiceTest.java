package com.pavelshapel.multi.threading.task.second.service;

import com.pavelshapel.multi.threading.task.second.entity.Category;
import com.pavelshapel.multi.threading.task.second.entity.Logs;
import com.pavelshapel.multi.threading.task.second.entity.SubTask;
import com.pavelshapel.multi.threading.task.second.entity.Task;
import com.pavelshapel.multi.threading.task.second.repository.LogsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
//@ExtendWith({
//        MongoDBExtension.class,
//        PostgreSQLExtension.class
//})
class TaskServiceTest {
    public static final long TEST_ID = -1;

    @Autowired
    private DataSeeder dataSeeder;
    @Autowired
    private TaskService taskService;
    @Autowired
    private LogsRepository logsRepository;

    @BeforeEach
    void setUp() {
        logsRepository.deleteAll();;
        taskService.deleteAll();
        dataSeeder.seeding();
    }

    @Test
    void getAll_ShouldReturnAllTasks() {
        List<Task> tasks = taskService.findAll();
        tasks.forEach(System.out::println);

        assertThat(tasks.size())
                .isEqualTo(DataSeeder.TASKS_COUNT);
    }

    @Test
    void findByDeadlineLessThanEqual_ShouldReturnOverdueTasks() {
        Date deadline = new Date();
        List<Task> tasks = taskService.findByDeadlineLessThanEqual(deadline);
        tasks.forEach(System.out::println);

        tasks.forEach(task -> assertThat(task.getDeadline()).isBeforeOrEqualTo(deadline));
    }

    @ParameterizedTest
    @EnumSource(Category.class)
    void findByCategoryEquals_ShouldReturnAppropriateTasks(Category category) {
        List<Task> tasks = taskService.findByCategoryEquals(category);
        tasks.forEach(System.out::println);

        tasks.forEach(task -> assertThat(task.getCategory()).isEqualTo(category));
    }

    @ParameterizedTest
    @EnumSource(Category.class)
    void findByCategoryEquals_ShouldReturnAppropriateSubTasks(Category category) {
        List<SubTask> subTasks = taskService.findSubTasksByCategoryEquals(category);
        System.out.println(category);
        subTasks.forEach(System.out::println);
    }

    @Test
    void save_ShouldSaveTask() {
        Task savedTask = taskService.save(createTask());

        Task retrievedTask = taskService.findById(savedTask.getId());

        assertThat(retrievedTask).isEqualTo(savedTask);
    }

    @Test
    void deleteById_ShouldDeleteTask() {
        Task savedTask = taskService.save(createTask());

        Long taskId = savedTask.getId();
        taskService.deleteById(taskId);

        assertThatThrownBy(() -> taskService.findById(taskId))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void findByDescriptionContainingIgnoreCase_ShouldReturnAllTasks(String description) {
        Task task = createTask();
        task.setDescription(description);
        taskService.save(task);

        String subDescription = description.length() > 1 ? description.substring(1) : description;
        List<Task> retrievedTask = taskService.findByDescriptionContainingIgnoreCase(subDescription.toLowerCase());

        assertThat(retrievedTask.stream()
                .map(Task::getId)
                .anyMatch(id -> task.getId().equals(id)))
                .isTrue();
    }

    @Test
    void getAll_ShouldSaveLog() {
        List<Task> tasks = taskService.findAll();
        List<Logs> all = logsRepository.findAll();
        all.forEach(System.out::println);
    }

    private Task createTask() {
        return dataSeeder.createTask(TEST_ID);
    }
}