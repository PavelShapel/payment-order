package com.pavelshapel.common.module.dto.service.location;

import com.pavelshapel.web.spring.boot.starter.web.converter.AbstractDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocationDto extends AbstractDto {
    String name;
    Long locationTypeId;
    Long parentId;
}

