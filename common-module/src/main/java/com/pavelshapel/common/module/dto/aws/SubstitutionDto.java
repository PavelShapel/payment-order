package com.pavelshapel.common.module.dto.aws;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pavelshapel.core.spring.boot.starter.api.model.S3Transferred;
import com.pavelshapel.core.spring.boot.starter.api.util.SubstitutionProperties;
import com.pavelshapel.core.spring.boot.starter.impl.model.MapS3Transferred;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubstitutionDto {
    @JsonDeserialize(as = MapS3Transferred.class)
    S3Transferred transferred;
    SubstitutionProperties properties;
}

