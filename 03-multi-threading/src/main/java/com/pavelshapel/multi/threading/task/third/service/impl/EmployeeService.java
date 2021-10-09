package com.pavelshapel.multi.threading.task.third.service.impl;

import com.pavelshapel.multi.threading.task.third.entity.Employee;
import com.pavelshapel.multi.threading.task.third.service.AbstractService;
import com.pavelshapel.multi.threading.task.third.service.EmployeeRepositoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService extends AbstractService implements EmployeeRepositoryService {
    @Override
    public Employee get(Long id) {
        return getEntityGenerator().getEmployees().stream()
                .filter(employee -> id.equals(employee.getId()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Employee> getAll() {
        return getEntityGenerator().getEmployees();
    }

    @Override
    public List<Employee> getHiredEmployees() {
        return getAll().stream()
                .filter(Employee::getHired)
                .collect(Collectors.toList());
    }
}
