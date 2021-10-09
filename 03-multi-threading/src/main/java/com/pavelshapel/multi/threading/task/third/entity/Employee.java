package com.pavelshapel.multi.threading.task.third.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee implements Entity {
    Long id;
    String name;
    Boolean hired;
    Salary salary;
}
