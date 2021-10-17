package com.pavelshapel.multi.threading.task.second.entity;

import com.pavelshapel.multi.threading.task.second.repository.listener.CascadeSave;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    @Id
    Long id;
    Date dateOfCreation;
    Date deadline;
    String name;
    String description;
    Category category;
    @DBRef
    @CascadeSave
    List<SubTask> subTasks;
}
