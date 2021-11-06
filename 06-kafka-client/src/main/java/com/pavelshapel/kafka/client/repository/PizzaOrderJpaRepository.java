package com.pavelshapel.kafka.client.repository;

import com.pavelshapel.jpa.spring.boot.starter.repository.AbstractJpaRepository;
import com.pavelshapel.kafka.client.entity.PizzaOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaOrderJpaRepository extends AbstractJpaRepository<PizzaOrder> {
}
