package com.pavelshapel.kafka.client.kafka;

import com.pavelshapel.common.module.dto.kafka.client.PizzaOrderDto;
import com.pavelshapel.kafka.spring.boot.starter.config.AbstractKafkaConsumerConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PizzaOrderKafkaConsumerConfig extends AbstractKafkaConsumerConfig<PizzaOrderDto> {
}
