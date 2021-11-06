package com.pavelshapel.kafka.client.repository.search;

import com.pavelshapel.jpa.spring.boot.starter.repository.search.SearchSpecification;
import com.pavelshapel.kafka.client.entity.PizzaOrder;
import org.springframework.stereotype.Component;

@Component
public class PizzaOrderSearchSpecification extends SearchSpecification<PizzaOrder> {
}
