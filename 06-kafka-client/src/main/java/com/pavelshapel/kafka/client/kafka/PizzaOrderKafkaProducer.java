package com.pavelshapel.kafka.client.kafka;

import com.pavelshapel.common.module.dto.kafka.client.PizzaOrderDto;
import com.pavelshapel.kafka.spring.boot.starter.service.AbstractKafkaProducer;
import org.springframework.stereotype.Service;

@Service
public class PizzaOrderKafkaProducer extends AbstractKafkaProducer<PizzaOrderDto> {

}
