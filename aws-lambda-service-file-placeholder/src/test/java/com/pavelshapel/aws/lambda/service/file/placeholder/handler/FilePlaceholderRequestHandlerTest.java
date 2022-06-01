package com.pavelshapel.aws.lambda.service.file.placeholder.handler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.tests.annotations.Event;
import com.pavelshapel.aws.lambda.service.file.placeholder.config.FilePlaceholderS3AwsConfiguration;
import com.pavelshapel.aws.lambda.service.file.placeholder.model.SubstitutionSetting;
import com.pavelshapel.aws.spring.boot.starter.AwsStarterAutoConfiguration;
import com.pavelshapel.aws.spring.boot.starter.api.service.BucketHandler;
import com.pavelshapel.aws.spring.boot.starter.impl.model.MapS3Transferred;
import com.pavelshapel.core.spring.boot.starter.CoreStarterAutoConfiguration;
import com.pavelshapel.core.spring.boot.starter.api.util.SubstitutionProperties;
import com.pavelshapel.core.spring.boot.starter.api.util.SubstitutionUtils;
import com.pavelshapel.json.spring.boot.starter.JsonStarterAutoConfiguration;
import com.pavelshapel.json.spring.boot.starter.converter.JsonConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(
        classes = {
                FilePlaceholderS3AwsConfiguration.class,
                FilePlaceholderRequestHandler.class,
                CoreStarterAutoConfiguration.class,
                JsonStarterAutoConfiguration.class,
                AwsStarterAutoConfiguration.class
        })
class FilePlaceholderRequestHandlerTest {
    public static final String TEMPLATE_FILE = "/akbb-payment-order-template.txt";

    @SpyBean
    private SubstitutionUtils substitutionUtils;
    @SpyBean
    private JsonConverter jsonConverter;
    @MockBean
    private BucketHandler bucketHandler;
    @Autowired
    private FilePlaceholderRequestHandler filePlaceholderRequestHandler;


    @ParameterizedTest
    @Event(value = "src/test/resources/sqs-message.json", type = SQSEvent.class)
    void apply_WithValidParam_ShouldReturnHttpOk(SQSEvent sqsEvent) {
        doReturn(true).when(bucketHandler).isBucketExists(anyString());
        doReturn(true).when(bucketHandler).isObjectExist(anyString(), anyString());
        doReturn(getClass().getResourceAsStream(TEMPLATE_FILE)).when(bucketHandler).downloadObject(anyString(), anyString());
        doReturn(EMPTY).when(bucketHandler).uploadObject(anyString(), anyString(), anyString());

        APIGatewayProxyResponseEvent response = filePlaceholderRequestHandler.apply(sqsEvent);

        assertThat(response.getStatusCode())
                .isEqualTo(OK.value());
    }

    @ParameterizedTest
    @Event(value = "src/test/resources/sqs-message.json", type = SQSEvent.class)
    void apply_WithBucketNotExists_ShouldReturnHttpInternalError(SQSEvent sqsEvent) {
        doReturn(false).when(bucketHandler).isBucketExists(anyString());

        APIGatewayProxyResponseEvent response = filePlaceholderRequestHandler.apply(sqsEvent);

        assertThat(response.getStatusCode())
                .isEqualTo(INTERNAL_SERVER_ERROR.value());
    }

    @ParameterizedTest
    @Event(value = "src/test/resources/sqs-message.json", type = SQSEvent.class)
    void apply_WithFileNotExists_ShouldReturnHttpInternalError(SQSEvent sqsEvent) {
        doReturn(false).when(bucketHandler).isBucketExists(anyString());

        APIGatewayProxyResponseEvent response = filePlaceholderRequestHandler.apply(sqsEvent);

        assertThat(response.getStatusCode())
                .isEqualTo(INTERNAL_SERVER_ERROR.value());
    }

    @ParameterizedTest
    @Event(value = "src/test/resources/sqs-message.json", type = SQSEvent.class)
    void apply_WithNullPropertiesSubstitutionSetting_ShouldReturnHttpInternalError(SQSEvent sqsEvent) {
        SubstitutionSetting substitutionSetting = new SubstitutionSetting();
        substitutionSetting.setTransferred(new MapS3Transferred());
        substitutionSetting.setProperties(new SubstitutionProperties());
        doReturn(substitutionSetting).when(jsonConverter).jsonToPojo(anyString(), any());

        APIGatewayProxyResponseEvent response = filePlaceholderRequestHandler.apply(sqsEvent);

        assertThat(response.getStatusCode())
                .isEqualTo(INTERNAL_SERVER_ERROR.value());
    }
}