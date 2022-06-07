package com.pavelshapel.aws.lambda.service.nbrb.handler;

import com.pavelshapel.aws.lambda.service.nbrb.AbstractTest;
import com.pavelshapel.aws.lambda.service.nbrb.model.Nbrb;
import com.pavelshapel.common.module.dto.aws.NbrbDto;
import com.pavelshapel.common.module.dto.aws.RatedDto;
import com.pavelshapel.json.spring.boot.starter.converter.JsonConverter;
import com.pavelshapel.test.spring.boot.starter.container.DynamoDBExtension;
import com.pavelshapel.web.spring.boot.starter.web.AbstractDaoRestController;
import com.pavelshapel.webflux.spring.boot.starter.api.ApiService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@ExtendWith(DynamoDBExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class NbrbRequestHandlerTest extends AbstractTest {
    @MockBean
    AbstractDaoRestController<String, Nbrb, NbrbDto> nbrbDaoRestController;
    @MockBean
    ApiService<RatedDto, NbrbDto> nbrbApiService;
    @Autowired
    JsonConverter jsonConverter;
    @Autowired
    NbrbRequestHandler nbrbRequestHandler;

    @Test
    void apply_WithValidParamsDataNotPresentInDatabase_ShouldMakeExternalRestCallAndSaveResponse() {
//        RatedDto ratedDto = createTestRatedDto();
//        NbrbDto nbrbDto = createTestNbrbDto();
//        doReturn(new ResponseEntity<>(EMPTY_LIST, OK)).when(nbrbDaoRestController).findAll(anyList());
//        doReturn(new ResponseEntity<>(nbrbDto, OK)).when(nbrbDaoRestController).save(any(NbrbDto.class));
//        doReturn(nbrbDto).when(nbrbApiService).get(any(RatedDto.class));
//        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
//        request.setBody(jsonConverter.pojoToJson(ratedDto));
//
//        APIGatewayProxyResponseEvent response = nbrbRequestHandler.apply(request);
//
//        assertThat(response.getStatusCode())
//                .isEqualTo(OK.value());
    }
}