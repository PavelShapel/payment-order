package com.pavelshapel.aws.lambda.service.location.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum LocationType {
    PLANET("планета", false),
    MAINLAND("материк", false),
    COUNTRY("страна", true),
    REGION("регион/область", true),
    CITY("город", true),
    STREET("улица", true),
    HOUSE("дом", true),
    FLAT("квартира", true),
    OFFICE("офис", true);

    String name;
    boolean printable;
}
