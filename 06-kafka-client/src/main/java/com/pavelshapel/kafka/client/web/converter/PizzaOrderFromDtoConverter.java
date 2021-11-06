package com.pavelshapel.kafka.client.web.converter;

import com.pavelshapel.common.module.dto.kafka.client.PizzaOrderDto;
import com.pavelshapel.kafka.client.entity.PizzaOrder;
import com.pavelshapel.kafka.client.entity.Pizza;
import com.pavelshapel.web.spring.boot.starter.web.converter.DtoConverter;
import com.pavelshapel.web.spring.boot.starter.web.converter.FromDtoConverter;

@DtoConverter
public class PizzaOrderFromDtoConverter implements FromDtoConverter<PizzaOrderDto, PizzaOrder> {
    @Override
    public PizzaOrder convert(PizzaOrderDto pizzaOrderDto) {
        PizzaOrder pizzaOrder = new PizzaOrder();
        pizzaOrder.setId(pizzaOrderDto.getId());
        pizzaOrder.setPizza(Pizza.valueOf(pizzaOrderDto.getPizza()));
        pizzaOrder.setOrderTime(pizzaOrderDto.getOrderTime());
        pizzaOrder.setCookingStartTime(pizzaOrderDto.getCookingStartTime());
        pizzaOrder.setCookingEndTime(pizzaOrderDto.getCookingEndTime());
        pizzaOrder.setCourierTime(pizzaOrderDto.getCourierTime());
        return pizzaOrder;
    }
}
