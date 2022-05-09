package com.pavelshapel.common.module.dto.aws;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pavelshapel.core.spring.boot.starter.impl.model.AbstractDto;
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
public class NbrbDto extends AbstractDto<String> {
    public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    @JsonProperty("Date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
    Date date;
    @JsonProperty("Cur_Abbreviation")
    String abbreviation;
    @JsonProperty("Cur_Scale")
    Integer scale;
    @JsonProperty("Cur_Name")
    String name;
    @JsonProperty("Cur_OfficialRate")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT)
    Double rate;
}

