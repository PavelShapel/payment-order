package com.pavelshapel.common.module.dto.kafka;

import lombok.Getter;
import org.apache.kafka.clients.admin.NewTopic;

public enum KafkaTopic {
    ORDER(new NewTopic("order", 3, (short) 2)),
    NOTIFICATION(new NewTopic("notification", 3, (short) 2));

    @Getter
    private final NewTopic topic;

    KafkaTopic(NewTopic topic) {
        this.topic = topic;
    }
}