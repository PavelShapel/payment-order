package com.pavelshapel.aws.lambda.service.file.placeholder.handler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.tests.annotations.Event;
import com.pavelshapel.aws.lambda.service.file.placeholder.config.FilePlaceholderS3AwsConfig;
import com.pavelshapel.aws.spring.boot.starter.AwsStarterAutoConfiguration;
import com.pavelshapel.aws.spring.boot.starter.api.util.BucketHandler;
import com.pavelshapel.core.spring.boot.starter.CoreStarterAutoConfiguration;
import com.pavelshapel.core.spring.boot.starter.api.util.SubstitutionUtils;
import com.pavelshapel.json.spring.boot.starter.JsonStarterAutoConfiguration;
import com.pavelshapel.json.spring.boot.starter.converter.JsonConverter;
import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@SpringBootTest(
        classes = {
                FilePlaceholderS3AwsConfig.class,
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


//    @Test
//    void verifyBucket_WithValidParam_ShouldCreateFile(SQSEvent event) {
//        doReturn(false).when(bucketHandler).isBucketExists(anyString());
//
//        assertThatThrownBy(() -> coreSubstitutionUtils.replace(SOURCE, null))
//                .hasMessageContainingAll(SOURCE, NULL)
//                .isInstanceOf(IllegalArgumentException.class);
//    }

    @SneakyThrows
    @ParameterizedTest
    @Event(value = "src/test/resources/sqs-message.json", type = SQSEvent.class)
    void verifyBucket_WithValidParam_ShouldCreateFile(SQSEvent sqsEvent) {
        doReturn(true).when(bucketHandler).isBucketExists(anyString());
        doReturn(true).when(bucketHandler).isObjectExist(anyString(), anyString());
        doReturn(getClass().getResourceAsStream(TEMPLATE_FILE)).when(bucketHandler).downloadObject(anyString(), anyString());
        doNothing().when(bucketHandler).uploadObject(anyString(), anyString(), anyString());

        APIGatewayProxyResponseEvent response = filePlaceholderRequestHandler.apply(sqsEvent);

        assertThat(response.getStatusCode())
                .isEqualTo(200);
    }
}