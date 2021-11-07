package com.pavelshapel.kafka.courier.kafka;

import com.pavelshapel.common.module.dto.kafka.PizzaOrderDto;
import com.pavelshapel.kafka.spring.boot.starter.config.AbstractKafkaConsumerConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PizzaOrderKafkaConsumerConfig extends AbstractKafkaConsumerConfig<PizzaOrderDto> {
}
