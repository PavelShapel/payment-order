package com.pavelshapel.common.module.dto.aws;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.pavelshapel.core.spring.boot.starter.impl.model.AbstractDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NbrbDto extends AbstractDto<String> {
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    @JsonProperty("Date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate date;
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

