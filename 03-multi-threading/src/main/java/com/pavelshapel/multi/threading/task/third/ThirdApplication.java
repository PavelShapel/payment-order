package com.pavelshapel.multi.threading.task.third;

import com.pavelshapel.multi.threading.task.third.entity.Employee;
import com.pavelshapel.multi.threading.task.third.entity.Salary;
import com.pavelshapel.multi.threading.task.third.service.impl.EmployeeService;
import com.pavelshapel.multi.threading.task.third.service.impl.SalaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class ThirdApplication implements CommandLineRunner {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private SalaryService salaryService;

    public static void main(String[] args) {
        SpringApplication.run(ThirdApplication.class, args);
    }

    @Override
    public void run(String... args) {
        getHiredEmployees()
                .thenCompose(employees -> allOf(employees.stream()
                        .map(this::setSalary)
                        .collect(Collectors.toList())))
                .thenAcceptAsync(this::logEmployee)
                .join();
    }

    private <T> CompletableFuture<List<T>> allOf(Collection<CompletableFuture<T>> futures) {
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(value -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));
    }

    CompletableFuture<List<Employee>> getHiredEmployees() {
        log.info("get hired employees");
        return CompletableFuture.supplyAsync(() -> employeeService.getHiredEmployees());
    }

    CompletableFuture<Employee> setSalary(Employee employee) {
        log.info("set salary for employee [{}]", employee);
        return CompletableFuture.supplyAsync(() -> setEmployeeSalary(employee));
    }

    private void logEmployee(List<Employee> employees) {
        employees.forEach(employee -> log.info("{}", employee));
    }

    private Employee setEmployeeSalary(Employee employee) {
        Salary salary = salaryService.getSalary(employee.getId());
        employee.setSalary(salary);
        return employee;
    }
}
