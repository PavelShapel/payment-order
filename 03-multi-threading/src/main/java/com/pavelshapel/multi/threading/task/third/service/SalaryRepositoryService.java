package com.pavelshapel.multi.threading.task.third.service;

import com.pavelshapel.multi.threading.task.third.entity.Salary;

public interface SalaryRepositoryService extends RepositoryService<Salary> {
    Salary getSalary(Long employeeId);
}
