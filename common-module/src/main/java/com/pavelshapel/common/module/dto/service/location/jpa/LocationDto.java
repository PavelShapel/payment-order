package com.pavelshapel.common.module.dto.service.location.jpa;

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
public class LocationDto extends AbstractDto<Long> {
    String name;
    Long locationTypeId;
    Long parentId;
}

