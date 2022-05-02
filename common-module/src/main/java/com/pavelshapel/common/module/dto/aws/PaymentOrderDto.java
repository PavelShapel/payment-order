package com.pavelshapel.common.module.dto.aws;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentOrderDto {
    String number;
}

