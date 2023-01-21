package com.pavelshapel.common.module.dto.aws.destination;

import lombok.Value;

@Value
public class Destination {
    S3ObjectDto source;
    S3ObjectDto target;
}
