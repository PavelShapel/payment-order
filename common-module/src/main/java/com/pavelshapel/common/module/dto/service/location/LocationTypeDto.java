package com.pavelshapel.common.module.dto.service.location;

import com.pavelshapel.web.spring.boot.starter.web.converter.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LocationTypeDto extends AbstractDto {
    String name;
}