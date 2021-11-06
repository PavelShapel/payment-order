package com.pavelshapel.kafka.client.service;

import com.pavelshapel.common.module.dto.kafka.client.PizzaOrderDto;
import com.pavelshapel.common.module.dto.kafka.topic.KafkaTopic;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.AbstractJpaService;
import com.pavelshapel.kafka.client.entity.PizzaOrder;
import com.pavelshapel.kafka.spring.boot.starter.service.KafkaProducer;
import com.pavelshapel.web.spring.boot.starter.web.converter.ToDtoConverter;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PizzaOrderJpaService extends AbstractJpaService<PizzaOrder> {
    @Autowired
    @Getter
    private KafkaProducer<PizzaOrderDto> kafkaProducer;
    @Autowired
    private ToDtoConverter<PizzaOrder, PizzaOrderDto> pizzaOrderToDtoConverter;

    public PizzaOrderJpaService() {
        super(PizzaOrder.class);
    }

    @Override
    public PizzaOrder create() {
        return new PizzaOrder();
    }

    @Override
    public PizzaOrder getParent(PizzaOrder pizzaOrder) {
        return null;
    }

    @Override
    public PizzaOrder save(PizzaOrder entity) {
        entity.setOrderTime(new Date());
        PizzaOrder pizzaOrder = super.save(entity);
        pizzaOrderToDtoConverter
                .andThen(pizzaOrderDto -> kafkaProducer.send(KafkaTopic.ORDER.getTopic().name(), pizzaOrderDto))
                .convert(pizzaOrder);
        return pizzaOrder;
    }
}