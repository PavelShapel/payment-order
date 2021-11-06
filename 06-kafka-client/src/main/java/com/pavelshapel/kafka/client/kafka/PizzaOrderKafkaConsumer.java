package com.pavelshapel.kafka.client.kafka;

import com.pavelshapel.common.module.dto.kafka.PizzaOrderDto;
import com.pavelshapel.kafka.client.entity.PizzaOrder;
import com.pavelshapel.kafka.client.service.PizzaOrderJpaService;
import com.pavelshapel.kafka.spring.boot.starter.config.AbstractKafkaConsumerConfig;
import com.pavelshapel.kafka.spring.boot.starter.service.KafkaConsumer;
import com.pavelshapel.web.spring.boot.starter.web.converter.FromDtoConverter;
import com.pavelshapel.web.spring.boot.starter.web.converter.ToDtoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PizzaOrderKafkaConsumer implements KafkaConsumer<PizzaOrderDto> {
    public static final String NOTIFICATION_TOPIC = "notification";

    @Autowired
    private PizzaOrderJpaService jpaService;
    @Autowired
    private FromDtoConverter<PizzaOrderDto, PizzaOrder> fromDtoConverter;
    @Autowired
    private ToDtoConverter<PizzaOrder, PizzaOrderDto> toDtoConverter;

    @Override
    @KafkaListener(topics = NOTIFICATION_TOPIC, containerFactory = AbstractKafkaConsumerConfig.SINGLE_FACTORY)
    public PizzaOrderDto receive(PizzaOrderDto dto) {
        log.info("consume.success [{}]", dto);
        return fromDtoConverter
                .andThen(this::updatePizzaOrder)
                .andThen(this::convertPizzaOrderToDto)
                .convert(dto);
    }

    private PizzaOrderDto convertPizzaOrderToDto(PizzaOrder pizzaOrder) {
        return toDtoConverter.convert(pizzaOrder);
    }

    private PizzaOrder updatePizzaOrder(PizzaOrder pizzaOrder) {
        return jpaService.update(pizzaOrder.getId(), pizzaOrder);
    }
}
