package com.pavelshapel.aws.lambda;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.pavelshapel.aws.lambda.entity.Product;
import com.pavelshapel.aws.lambda.entity.Response;
import com.pavelshapel.aws.lambda.repository.ProductRepository;

import java.util.Optional;

public class ProductHandler implements RequestHandler<Product, Response> {
    private final Regions REGION = Regions.EU_WEST_1;
    private final AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient();
    private final DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
    private final ProductRepository productRepository = new ProductRepository(dynamoDBMapper);

    public Response handleRequest(Product product, Context context) {
        amazonDynamoDB.setRegion(Region.getRegion(REGION));
        return Optional.ofNullable(product.getId())
                .flatMap(productRepository::findOne)
                .map(this::createResponseIfPresent)
                .orElseGet(() -> saveAndCreateResponse(product, context));
    }

    private Response createResponseIfPresent(Product product) {
        return new Response(String.format("entity [%s] present", product));
    }

    private Response saveAndCreateResponse(Product product, Context context) {
        context.getLogger().log("before save");
        productRepository.save(product);
        return new Response(String.format("save entity [%s] success", product));
    }
}