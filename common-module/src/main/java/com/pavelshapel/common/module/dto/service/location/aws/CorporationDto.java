package com.pavelshapel.common.module.dto.service.location.aws;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;
import com.pavelshapel.core.spring.boot.starter.impl.model.AbstractParentalDto;
import com.pavelshapel.core.spring.boot.starter.impl.model.TypedDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CorporationDto extends AbstractParentalDto<String> {
    @JsonDeserialize(as = TypedDto.class)
    Typed typed;
}

