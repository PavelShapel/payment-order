package com.pavelshapel.multi.threading.task.third.service.impl;

import com.pavelshapel.multi.threading.task.third.entity.Salary;
import com.pavelshapel.multi.threading.task.third.service.AbstractService;
import com.pavelshapel.multi.threading.task.third.service.SalaryRepositoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SalaryService extends AbstractService implements SalaryRepositoryService {
    @Override
    public Salary get(Long id) {
        return getEntityGenerator().getSalaries().stream()
                .filter(salary -> id.equals(salary.getId()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Salary> getAll() {
        return getEntityGenerator().getSalaries();
    }

    @Override
    public Salary getSalary(Long employeeId) {
        List<Salary> salaries = getAll();
        return salaries.stream()
                .skip(ThreadLocalRandom.current().nextInt(salaries.size()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
