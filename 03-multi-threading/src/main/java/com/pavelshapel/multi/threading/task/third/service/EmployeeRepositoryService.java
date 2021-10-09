package com.pavelshapel.multi.threading.task.third.service;

import com.pavelshapel.multi.threading.task.third.entity.Employee;

import java.util.List;

public interface EmployeeRepositoryService extends RepositoryService<Employee> {
    List<Employee> getHiredEmployees();
}
