package com.pavelshapel.nosql.task.first.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class LocationTypeMongo {
    @Id
    private long id;

    @Indexed(unique = true)
    private String name;
}