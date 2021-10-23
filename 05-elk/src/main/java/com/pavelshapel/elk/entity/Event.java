package com.pavelshapel.elk.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "production")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Event {
    @Id
    String id;
    String title;
    EventType eventType;
    String description;
    @Field(type = FieldType.Nested, includeInParent = true)
    List<SubEvent> subEvents;
}
