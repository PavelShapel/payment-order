package com.pavelshapel.common.module.dto.kafka.topic;

import lombok.Getter;
import org.apache.kafka.clients.admin.NewTopic;

public enum KafkaTopic {
    ORDER(new NewTopic("order", 3, (short) 1)),
    NOTIFICATION(new NewTopic("notification", 3, (short) 1));

    @Getter
    private final NewTopic topic;

    KafkaTopic(NewTopic topic) {
        this.topic = topic;
    }
}