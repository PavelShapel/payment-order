package com.pavelshapel.common.module.dto.jpa;

import com.pavelshapel.core.spring.boot.starter.impl.model.AbstractDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocationTypeDto extends AbstractDto<Long> {
    String name;
}