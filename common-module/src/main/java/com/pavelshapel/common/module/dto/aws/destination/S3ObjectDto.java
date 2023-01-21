package com.pavelshapel.common.module.dto.aws.destination;

import lombok.Value;

@Value
public class S3ObjectDto {
    String bucket;
    String key;
    String payload;
}
