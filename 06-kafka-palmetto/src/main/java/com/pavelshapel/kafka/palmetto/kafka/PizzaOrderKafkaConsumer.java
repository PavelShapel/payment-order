package com.pavelshapel.kafka.palmetto.kafka;

import com.pavelshapel.common.module.dto.kafka.Pizza;
import com.pavelshapel.common.module.dto.kafka.PizzaOrderDto;
import com.pavelshapel.kafka.spring.boot.starter.config.AbstractKafkaConsumerConfig;
import com.pavelshapel.kafka.spring.boot.starter.service.KafkaConsumer;
import com.pavelshapel.kafka.spring.boot.starter.service.KafkaProducer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PizzaOrderKafkaConsumer implements KafkaConsumer<PizzaOrderDto> {
    public static final String ORDER_TOPIC = "order";
    public static final String NOTIFICATION_TOPIC = "notification";

    @Autowired
    private KafkaProducer<PizzaOrderDto> kafkaProducer;

    @Override
    @KafkaListener(topics = ORDER_TOPIC, containerFactory = AbstractKafkaConsumerConfig.SINGLE_FACTORY)
    public PizzaOrderDto receive(PizzaOrderDto dto) {
        log.info("consume.success [{}]", dto);
        startCooking(dto);
        cooking(dto);
        stopCooking(dto);
        return dto;
    }

    private void startCooking(PizzaOrderDto dto) {
        dto.setCookingStartTime(new Date());
        kafkaProducer.send(NOTIFICATION_TOPIC, dto);
    }

    @SneakyThrows
    private void cooking(PizzaOrderDto dto){
        Pizza pizza = Pizza.valueOf(dto.getPizza());
        TimeUnit.SECONDS.sleep(pizza.getCookingDuration());
        log.info("cooking.success [{}]", dto);
    }

    private void stopCooking(PizzaOrderDto dto) {
        dto.setCookingEndTime(new Date());
        kafkaProducer.send(NOTIFICATION_TOPIC, dto);
    }
}
