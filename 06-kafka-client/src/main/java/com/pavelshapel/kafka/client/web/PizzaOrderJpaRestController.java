package com.pavelshapel.kafka.client.web;

import com.pavelshapel.common.module.dto.kafka.PizzaOrderDto;
import com.pavelshapel.jpa.spring.boot.starter.repository.search.SearchSpecification;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.JpaService;
import com.pavelshapel.kafka.client.entity.PizzaOrder;
import com.pavelshapel.web.spring.boot.starter.web.AbstractJpaRestController;
import com.pavelshapel.web.spring.boot.starter.web.converter.FromDtoConverter;
import com.pavelshapel.web.spring.boot.starter.web.converter.ToDtoConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pavelshapel.kafka.client.web.PizzaOrderJpaRestController.HOME_PATH;

@RestController
@RequestMapping(HOME_PATH)
public class PizzaOrderJpaRestController extends AbstractJpaRestController<PizzaOrder, PizzaOrderDto> {
    public static final String HOME_PATH = "/orders" + StringUtils.EMPTY;

    @Autowired
    public PizzaOrderJpaRestController(JpaService<PizzaOrder> pizzaOrderJpaService,
                                       SearchSpecification<PizzaOrder> pizzaOrderSearchSpecification,
                                       FromDtoConverter<PizzaOrderDto, PizzaOrder> pizzaOrderFromDtoConverter,
                                       ToDtoConverter<PizzaOrder, PizzaOrderDto> pizzaOrderToDtoConverter) {
        super(pizzaOrderJpaService, pizzaOrderSearchSpecification, pizzaOrderFromDtoConverter, pizzaOrderToDtoConverter);
    }
}
