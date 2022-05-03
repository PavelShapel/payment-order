package com.pavelshapel.aws.lambda.service.file.placeholder.handler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.pavelshapel.aws.lambda.service.file.placeholder.model.SubstitutionSetting;
import com.pavelshapel.aws.spring.boot.starter.api.model.S3Transferred;
import com.pavelshapel.aws.spring.boot.starter.api.util.BucketHandler;
import com.pavelshapel.core.spring.boot.starter.api.util.SubstitutionProperties;
import com.pavelshapel.core.spring.boot.starter.api.util.SubstitutionUtils;
import com.pavelshapel.json.spring.boot.starter.converter.JsonConverter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Collections.singletonMap;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.util.StringUtils.hasLength;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FilePlaceholderRequestHandler implements Function<SQSEvent, APIGatewayProxyResponseEvent> {
    public static final String BUCKET_DOES_NOT_EXIST_PATTERN = "bucket [%s] does not exist";
    public static final String FILE_DOES_NOT_EXIST_PATTERN = "file [%s] does not exist";
    BucketHandler bucketHandler;
    SubstitutionUtils substitutionUtils;
    JsonConverter jsonConverter;

    @Override
    public APIGatewayProxyResponseEvent apply(SQSEvent sqsEvent) {
        List<SubstitutionSetting> substitutionSettings = getSubstitutionSettings(sqsEvent);
        substitutionSettings.forEach(this::handleSubstitutionSetting);
        String body = substitutionSettings.stream()
                .map(SubstitutionSetting::getTransferred)
                .map(S3Transferred::toString)
                .collect(Collectors.joining(","));
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withHeaders(singletonMap(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .withBody(body);
    }

    private List<SubstitutionSetting> getSubstitutionSettings(SQSEvent sqsEvent) {
        return sqsEvent.getRecords().stream()
                .map(SQSEvent.SQSMessage::getBody)
                .map(json -> jsonConverter.jsonToPojo(json, SubstitutionSetting.class))
                .collect(Collectors.toList());
    }

    private void handleSubstitutionSetting(SubstitutionSetting substitutionSetting) {
        S3Transferred transferred = getTransferred(substitutionSetting);
        SubstitutionProperties properties = getProperties(substitutionSetting);
        verifyBucket(transferred.getSourceBucket());
        verifyBucket(transferred.getTargetBucket());
        verifyFile(transferred.getSourceBucket(), transferred.getSourceFile());
        transferFile(transferred, properties);
    }

    private S3Transferred getTransferred(SubstitutionSetting substitutionSetting) {
        return Optional.of(substitutionSetting)
                .map(SubstitutionSetting::getTransferred)
                .filter(transferred -> hasLength(transferred.getSourceBucket()))
                .filter(transferred -> hasLength(transferred.getSourceFile()))
                .filter(transferred -> hasLength(transferred.getTargetBucket()))
                .filter(transferred -> hasLength(transferred.getTargetFile()))
                .orElseThrow(() -> new IllegalArgumentException(substitutionSetting.getTransferred().toString()));
    }

    private SubstitutionProperties getProperties(SubstitutionSetting substitutionSetting) {
        return Optional.of(substitutionSetting)
                .map(SubstitutionSetting::getProperties)
                .orElseThrow(() -> new IllegalArgumentException(substitutionSetting.getProperties().toString()));
    }

    private void verifyBucket(String bucket) {
        Optional.of(bucketHandler.isBucketExists(bucket))
                .filter(Boolean.FALSE::equals)
                .ifPresent(unused -> throwBucketDoesNotExistException(bucket));
    }

    private void throwBucketDoesNotExistException(String bucket) {
        throw new IllegalArgumentException(String.format(BUCKET_DOES_NOT_EXIST_PATTERN, bucket));
    }

    private void verifyFile(String bucket, String file) {
        Optional.of(bucketHandler.isObjectExist(bucket, file))
                .filter(Boolean.FALSE::equals)
                .ifPresent(unused -> throwFileDoesNotExistException(file));
    }

    private void throwFileDoesNotExistException(String file) {
        throw new IllegalArgumentException(String.format(FILE_DOES_NOT_EXIST_PATTERN, file));
    }

    @SneakyThrows
    private void transferFile(S3Transferred s3Transferred, SubstitutionProperties substitutionProperties) {
        String targetFile = substitutionUtils.replace(s3Transferred.getTargetFile(), substitutionProperties);
        try (InputStream objectInputStream = bucketHandler.downloadObject(s3Transferred.getSourceBucket(), s3Transferred.getSourceFile())) {
            Optional.of(objectInputStream)
                    .map(inputStream -> substitutionUtils.replace(inputStream, substitutionProperties))
                    .ifPresent(payload -> bucketHandler.uploadObject(s3Transferred.getTargetBucket(), targetFile, payload));
        }
    }
}
