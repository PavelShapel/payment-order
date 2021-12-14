package com.pavelshapel.aws.dynamo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.pavelshapel.aws.dynamo.entity.Order;
import com.pavelshapel.aws.dynamo.repository.OrderRepository;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

public class Handler implements RequestHandler<DynamodbEvent, Void> {
    public static final String BUCKET_NAME = "com.pavelshapel.demo.bucket";
    public static final String INDEX_HTML = "index.html";
    private static final Regions REGION = Regions.EU_WEST_1;

    private final AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().withRegion(REGION).build();
    private final DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
    private final OrderRepository orderRepository = new OrderRepository(dynamoDBMapper);
    private final AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegion(REGION).build();

    @Override
    public Void handleRequest(DynamodbEvent dynamodbEvent, Context context) {
        createBucket(context);
        context.getLogger().log("trigger [" + dynamodbEvent + "]");
        handleMessage(context);
        return null;
    }

    private void handleMessage(Context context) {
        String html = new StringBuilder()
                .append("<!doctype html>")
                .append("<html xmlns=\"http://www.w3.org/1999/xhtml\">")
                .append(buildHeadTag())
                .append("<body>")
                .append("<h1 align=\"center\">").append(Order.class.getSimpleName()).append("</h1>")
                .append(buildTableTag(orderRepository.findAll()))
                .append("</body>")
                .append("</html>")
                .toString();
        saveToBucket(html, context);
    }

    @SneakyThrows
    private void saveToBucket(String html, Context context) {
        try (InputStream inputStream = IOUtils.toInputStream(html, Charset.defaultCharset())) {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType("text/html");
            s3client.putObject(BUCKET_NAME, INDEX_HTML, inputStream, objectMetadata);
            context.getLogger().log("save [" + INDEX_HTML + "]");
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

    private String buildTableTag(List<Order> orders) {
        String rows = orders.stream()
                .map(this::convertOrderToRowTag)
                .collect(Collectors.joining());
        return new StringBuilder()
                .append("<table border=\"1\" cellpadding=\"5\" width=\"80%\" align=\"center\">")
                .append("<tbody>")
                .append(rows)
                .append("</tbody>")
                .append("</table>")
                .toString();
    }

    private String buildHeadTag() {
        return new StringBuilder()
                .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("<title>").append(Order.class.getSimpleName()).append("</title>")
                .append("</head>")
                .toString();
    }

    private String convertOrderToRowTag(Order order) {
        return new StringBuilder()
                .append("<tr>")
                .append("<td>").append(order.getId()).append("</td>")
                .append("<td>").append(order.getOrderType()).append("</td>")
                .append("<td>").append(order.getValue()).append("</td>")
                .append("<td>").append(order.getCount()).append("</td>")
                .append("</tr>")
                .toString();
    }
}