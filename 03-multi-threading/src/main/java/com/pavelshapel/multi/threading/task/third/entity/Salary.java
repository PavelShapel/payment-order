package com.pavelshapel.multi.threading.task.third.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Salary implements Entity {
    Long id;
    Long value;
}
