package com.pavelshapel.multi.threading.task.third.service.impl;

import com.pavelshapel.multi.threading.task.third.entity.Employee;
import com.pavelshapel.multi.threading.task.third.entity.Salary;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
@Getter
public class EntityGenerator {
    public static final long DEFAULT_COUNT = 1000;
    public static final int MAX_NAME_LENGTH = 10;
    public static final int MAX_SALARY = 100;

    private List<Employee> employees;
    private List<Salary> salaries;

    @PostConstruct
    private void postConstruct() {
        generateEmployeesWithoutSalary();
        generateSalaries();
    }

    private void generateEmployeesWithoutSalary() {
        employees = LongStream.range(0, DEFAULT_COUNT)
                .mapToObj(index -> new Employee(
                        index,
                        RandomStringUtils.randomAlphanumeric(MAX_NAME_LENGTH),
                        ThreadLocalRandom.current().nextBoolean(),
                        null))
                .limit(DEFAULT_COUNT)
                .collect(Collectors.toList());
    }

    private void generateSalaries() {
        salaries = employees.stream()
                .map(employee -> new Salary(
                        employee.getId() * -1,
                        ThreadLocalRandom.current().nextLong(MAX_SALARY)))
                .collect(Collectors.toList());
    }
}
