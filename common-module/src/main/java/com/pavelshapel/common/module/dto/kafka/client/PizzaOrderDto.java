package com.pavelshapel.common.module.dto.kafka.client;

import com.pavelshapel.web.spring.boot.starter.web.converter.AbstractDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PizzaOrderDto extends AbstractDto {
    String pizza;
    Date orderTime;
    Date cookingStartTime;
    Date cookingEndTime;
    Date courierTime;
}
