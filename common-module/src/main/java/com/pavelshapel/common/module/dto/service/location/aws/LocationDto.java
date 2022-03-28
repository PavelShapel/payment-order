package com.pavelshapel.common.module.dto.service.location.aws;

import com.pavelshapel.core.spring.boot.starter.impl.model.AbstractParentalDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocationDto extends AbstractParentalDto<String> {
    String locationType;
}

