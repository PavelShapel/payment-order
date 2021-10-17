package com.pavelshapel.nosql.task.second.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Logs {
    String threadName;
    String level;
    String formattedMessage;
    String loggerName;
    Long timestamp;
}