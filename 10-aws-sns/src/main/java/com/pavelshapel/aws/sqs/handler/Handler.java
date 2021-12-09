package com.pavelshapel.aws.sqs.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.pavelshapel.aws.sqs.entity.Order;

import java.util.Map;

public class Handler implements RequestHandler<SQSEvent, Void> {
    public static final String APPLIED_QUEUE = "demo-applied-orders-queue";
    public static final String REJECTED_QUEUE = "demo-rejected-orders-queue";
    public static final Integer THRESHOLD = 100;

    private final AmazonSQS amazonSQSClient = AmazonSQSClientBuilder.defaultClient();
    private final JsonParser jsonParser = new JsonParser();

    @Override
    public Void handleRequest(SQSEvent sqsEvent, Context context) {
        for (SQSMessage sqsMessage : sqsEvent.getRecords()) {
            context.getLogger().log("receive message [" + sqsMessage + "]");
            handleMessage(sqsMessage, context);
        }
        return null;
    }

    private void handleMessage(SQSMessage sqsMessage, Context context) {
        Map body = jsonParser.jsonToMap(sqsMessage.getBody());
        Order order = jsonParser.jsonToPojo(body.get("Message").toString(), Order.class);
        context.getLogger().log("parse order [" + order + "]");
        forwardMessage(order, context);
    }

    private void forwardMessage(Order order, Context context) {
        String queueName = THRESHOLD.compareTo(order.getCount() * order.getValue()) < 0 ? REJECTED_QUEUE : APPLIED_QUEUE;
        String queueUrl = amazonSQSClient.getQueueUrl(queueName).getQueueUrl();
        context.getLogger().log("forward to queue [" + queueName + "]");
        SendMessageRequest messageRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(jsonParser.pojoToJson(order));
        SendMessageResult sendMessageResult = amazonSQSClient.sendMessage(messageRequest);
        context.getLogger().log("message result [" + sendMessageResult + "]");
    }
}