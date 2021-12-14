package com.pavelshapel.common.module.dto.service.location;

import com.pavelshapel.web.spring.boot.starter.web.dto.rds.AbstractRdsDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocationTypeDto extends AbstractRdsDto {
    String name;
}