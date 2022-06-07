package com.pavelshapel.aws.lambda.service.nbrb.handler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

import static com.pavelshapel.aws.lambda.service.nbrb.web.NbrbDaoRestController.HOME_PATH;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(HOME_PATH + "/aws")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FuncApplication implements ApplicationContextInitializer<GenericApplicationContext> {

    @Autowired
    Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> nbrbRequestHandler;
    String functionName = "nbrbRequestHandler";

    @Override
    public void initialize(GenericApplicationContext context) {
//        context.registerBean(functionName, FunctionRegistration.class,
//                () -> new FunctionRegistration<>(function)
//                        .type(FunctionType.from(APIGatewayProxyRequestEvent.class).to(APIGatewayProxyResponseEvent.class).getType()));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<APIGatewayProxyResponseEvent> save(@RequestBody APIGatewayProxyRequestEvent dto) {
        return ResponseEntity.ok(nbrbRequestHandler.apply(dto));
    }
}