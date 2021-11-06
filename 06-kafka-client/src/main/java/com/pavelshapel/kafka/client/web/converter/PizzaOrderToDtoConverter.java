package com.pavelshapel.kafka.client.web.converter;

import com.pavelshapel.common.module.dto.kafka.PizzaOrderDto;
import com.pavelshapel.kafka.client.entity.PizzaOrder;
import com.pavelshapel.web.spring.boot.starter.web.converter.DtoConverter;
import com.pavelshapel.web.spring.boot.starter.web.converter.ToDtoConverter;

@DtoConverter
public class PizzaOrderToDtoConverter implements ToDtoConverter<PizzaOrder, PizzaOrderDto> {
    @Override
    public PizzaOrderDto convert(PizzaOrder pizzaOrder) {
        PizzaOrderDto pizzaOrderDto = new PizzaOrderDto();
        pizzaOrderDto.setId(pizzaOrder.getId());
        pizzaOrderDto.setPizza(pizzaOrder.getPizza().name());
        pizzaOrderDto.setOrderTime(pizzaOrder.getOrderTime());
        pizzaOrderDto.setCookingStartTime(pizzaOrder.getCookingStartTime());
        pizzaOrderDto.setCookingEndTime(pizzaOrder.getCookingEndTime());
        pizzaOrderDto.setCourierTime(pizzaOrder.getCourierTime());
        return pizzaOrderDto;
    }
}
