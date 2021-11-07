package com.pavelshapel.kafka.courier.kafka;

import com.pavelshapel.common.module.dto.kafka.topic.KafkaTopic;
import com.pavelshapel.kafka.spring.boot.starter.config.AbstractKafkaTopicConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static java.util.Arrays.asList;

@Configuration
public class PizzaOrderKafkaTopicConfig extends AbstractKafkaTopicConfig {
    @Override
    protected List<NewTopic> getTopics() {
        return asList(KafkaTopic.ORDER.getTopic(), KafkaTopic.NOTIFICATION.getTopic());
    }
}
