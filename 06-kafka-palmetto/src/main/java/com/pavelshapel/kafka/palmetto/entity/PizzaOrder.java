package com.pavelshapel.kafka.palmetto.entity;

import com.pavelshapel.common.module.dto.kafka.Pizza;
import com.pavelshapel.jpa.spring.boot.starter.entity.AbstractVersionEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class PizzaOrder extends AbstractVersionEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Pizza pizza;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    Date orderTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    Date cookingStartTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    Date cookingEndTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    Date courierTime;
}