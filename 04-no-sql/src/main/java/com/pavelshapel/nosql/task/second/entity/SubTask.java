package com.pavelshapel.nosql.task.second.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubTask {
    @Id
    Long id;
    String name;
}