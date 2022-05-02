package com.pavelshapel.aws.lambda.service.file.placeholder.handler;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.tests.annotations.Event;
import com.pavelshapel.aws.lambda.service.file.placeholder.config.FilePlaceholderS3AwsConfig;
import com.pavelshapel.aws.spring.boot.starter.AwsStarterAutoConfiguration;
import com.pavelshapel.aws.spring.boot.starter.util.BucketHandler;
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

@SpringBootTest(
        classes = {
                FilePlaceholderS3AwsConfig.class,
                FilePlaceholderRequestHandler.class,
                CoreStarterAutoConfiguration.class,
                JsonStarterAutoConfiguration.class,
                AwsStarterAutoConfiguration.class
        })
class FilePlaceholderRequestHandlerTest {
    @SpyBean
    private SubstitutionUtils substitutionUtils;
    @SpyBean
    private JsonConverter jsonConverter;
    @MockBean
    private BucketHandler bucketHandler;
    @Autowired
    private FilePlaceholderRequestHandler filePlaceholderRequestHandler;


    @SneakyThrows
    @ParameterizedTest
    @Event(value = "src/test/resources/sqsMessage.json", type = SQSEvent.class)
    void apply_WithValidParam_ShouldCreateFile(SQSEvent event) {
        filePlaceholderRequestHandler.apply(event);
    }
}