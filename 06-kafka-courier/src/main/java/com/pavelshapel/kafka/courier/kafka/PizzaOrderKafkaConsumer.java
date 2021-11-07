package com.pavelshapel.kafka.courier.kafka;

import com.pavelshapel.common.module.dto.kafka.PizzaOrderDto;
import com.pavelshapel.kafka.spring.boot.starter.config.AbstractKafkaConsumerConfig;
import com.pavelshapel.kafka.spring.boot.starter.service.KafkaConsumer;
import com.pavelshapel.kafka.spring.boot.starter.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.util.Objects.nonNull;

@Service
@Slf4j
public class PizzaOrderKafkaConsumer implements KafkaConsumer<PizzaOrderDto> {
    public static final String NOTIFICATION_TOPIC = "notification";

    @Autowired
    private KafkaProducer<PizzaOrderDto> kafkaProducer;

    @Override
    @KafkaListener(topics = NOTIFICATION_TOPIC, containerFactory = AbstractKafkaConsumerConfig.SINGLE_FACTORY)
    public PizzaOrderDto receive(PizzaOrderDto dto) {
        log.info("consume.success [{}]", dto);
        if (nonNull(dto.getCookingEndTime())) {
            startDelivery(dto);
        }
        return dto;
    }

    private void startDelivery(PizzaOrderDto dto) {
        dto.setCourierTime(new Date());
        log.info("delivery.success [{}]", dto);
        kafkaProducer.send(NOTIFICATION_TOPIC, dto);
    }
}
