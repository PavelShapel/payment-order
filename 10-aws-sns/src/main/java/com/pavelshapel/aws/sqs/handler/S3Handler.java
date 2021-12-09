package com.pavelshapel.aws.sqs.handler;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.pavelshapel.aws.sqs.entity.Order;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.Charset;

public class S3Handler implements RequestHandler<SQSEvent, Void> {
    public static final String BUCKET_NAME = "demo-lambdass";
    public static final String LOG_OBJECT = "demo-log-object";

    private final JsonParser jsonParser = new JsonParser();
    private final AmazonS3 s3client = AmazonS3ClientBuilder
            .standard()
            .withRegion(Regions.EU_WEST_1)
            .build();

    @Override
    public Void handleRequest(SQSEvent sqsEvent, Context context) {
        createBucket(context);
        for (SQSEvent.SQSMessage sqsMessage : sqsEvent.getRecords()) {
            context.getLogger().log("receive message [" + sqsMessage + "]");
            handleMessage(sqsMessage, context);
        }
        return null;
    }

    @SneakyThrows
    private void handleMessage(SQSEvent.SQSMessage sqsMessage, Context context) {
        Order order = jsonParser.jsonToPojo(sqsMessage.getBody(), Order.class);
        context.getLogger().log("parse order [" + order + "]");
        saveOrder(order, context);
    }

    @SneakyThrows
    private void saveOrder(Order order, Context context) {
        try (InputStream inputStream = IOUtils.toInputStream(order.toString(), Charset.defaultCharset())) {
            s3client.putObject(BUCKET_NAME, LOG_OBJECT, inputStream, new ObjectMetadata());
            context.getLogger().log("save order [" + order + "]");
        }
    }

    private void createBucket(Context context) {
        if (s3client.doesBucketExistV2(BUCKET_NAME)) {
            context.getLogger().log("bucket is already exist [" + BUCKET_NAME + "]");
            return;
        }
        s3client.createBucket(BUCKET_NAME);
        context.getLogger().log("bucket created [" + BUCKET_NAME + "]");
    }
}
