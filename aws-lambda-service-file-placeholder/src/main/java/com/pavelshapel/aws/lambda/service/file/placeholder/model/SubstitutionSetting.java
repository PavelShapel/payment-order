package com.pavelshapel.aws.lambda.service.file.placeholder.model;

import com.pavelshapel.aws.spring.boot.starter.impl.model.MapS3Transferred;
import com.pavelshapel.core.spring.boot.starter.api.util.SubstitutionProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubstitutionSetting {
    MapS3Transferred transferred;
    SubstitutionProperties properties;
}

