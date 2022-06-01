package com.pavelshapel.aws.lambda.service.file.placeholder.handler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.pavelshapel.aws.lambda.service.file.placeholder.model.SubstitutionSetting;
import com.pavelshapel.aws.spring.boot.starter.api.model.S3Transferred;
import com.pavelshapel.aws.spring.boot.starter.api.service.BucketHandler;
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
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.util.StringUtils.hasLength;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FilePlaceholderRequestHandler implements Function<SQSEvent, APIGatewayProxyResponseEvent> {
    BucketHandler bucketHandler;
    SubstitutionUtils substitutionUtils;
    JsonConverter jsonConverter;

    @Override
    public APIGatewayProxyResponseEvent apply(SQSEvent sqsEvent) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(singletonMap(CONTENT_TYPE, APPLICATION_JSON_VALUE));
        String body;
        try {
            List<SubstitutionSetting> substitutionSettings = getSubstitutionSettings(sqsEvent);
            substitutionSettings.forEach(this::handleSubstitutionSetting);
            body = substitutionSettings.stream()
                    .map(SubstitutionSetting::getTransferred)
                    .map(S3Transferred::toString)
                    .collect(Collectors.joining(","));
            return response
                    .withStatusCode(OK.value())
                    .withBody(body);
        } catch (Exception exception) {
            body = jsonConverter.pojoToJson(singletonMap("exceptionMessage", exception.getMessage()));
            return response
                    .withStatusCode(INTERNAL_SERVER_ERROR.value())
                    .withBody(body);
        }
    }

    private List<SubstitutionSetting> getSubstitutionSettings(SQSEvent sqsEvent) {
        return sqsEvent.getRecords().stream()
                .map(SQSEvent.SQSMessage::getBody)
                .map(this::jsonToPojo)
                .collect(Collectors.toList());
    }

    private SubstitutionSetting jsonToPojo(String json) {
        return jsonConverter.jsonToPojo(json, SubstitutionSetting.class);
    }

    private void handleSubstitutionSetting(SubstitutionSetting substitutionSetting) {
        S3Transferred transferred = getTransferred(substitutionSetting);
        SubstitutionProperties properties = getProperties(substitutionSetting);
        verifyBucket(transferred.getSourceBucket());
        verifyBucket(transferred.getTargetBucket());
        verifyObject(transferred.getSourceBucket(), transferred.getSourceFile());
        transferObject(transferred, properties);
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
        throw new IllegalArgumentException("bucket [" + bucket + "] does not exist");
    }

    private void verifyObject(String bucket, String object) {
        Optional.of(bucketHandler.isObjectExist(bucket, object))
                .filter(Boolean.FALSE::equals)
                .ifPresent(unused -> throwObjectDoesNotExistException(object));
    }

    private void throwObjectDoesNotExistException(String object) {
        throw new IllegalArgumentException("object [" + object + "] does not exist");
    }

    @SneakyThrows
    private void transferObject(S3Transferred s3Transferred, SubstitutionProperties substitutionProperties) {
        String targetFile = substitutionUtils.replace(s3Transferred.getTargetFile(), substitutionProperties);
        try (InputStream objectInputStream = bucketHandler.downloadObject(s3Transferred.getSourceBucket(), s3Transferred.getSourceFile())) {
            Optional.of(objectInputStream)
                    .map(inputStream -> substitutionUtils.replace(inputStream, substitutionProperties))
                    .ifPresent(payload -> bucketHandler.uploadObject(s3Transferred.getTargetBucket(), targetFile, payload));
        }
    }
}
