//package com.pavelshapel.aws.lambda.service.nbrb.listener;
//
//import com.pavelshapel.aws.lambda.service.nbrb.model.Nbrb;
//import com.pavelshapel.aws.spring.boot.starter.api.util.BucketHandler;
//import com.pavelshapel.aws.spring.boot.starter.properties.AwsProperties;
//import com.pavelshapel.aws.spring.boot.starter.properties.nested.AbstractServiceProperties;
//import com.pavelshapel.common.module.dto.aws.NbrbDto;
//import com.pavelshapel.json.spring.boot.starter.converter.JsonConverter;
//import com.pavelshapel.web.spring.boot.starter.web.AbstractDaoRestController;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import lombok.experimental.FieldDefaults;
//import lombok.extern.java.Log;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.data.util.Pair;
//import org.springframework.lang.NonNull;
//import org.springframework.stereotype.Component;
//
//import java.io.InputStream;
//import java.util.Optional;
//
//import static java.util.logging.Level.INFO;
//
//@Log
//@Component
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@RequiredArgsConstructor
//public class NbrbContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
//    BucketHandler bucketHandler;
//    AbstractDaoRestController<String, Nbrb, NbrbDto> nbrbDaoRestController;
//    AwsProperties awsProperties;
//    JsonConverter jsonConverter;
//
//    @Override
//    @SneakyThrows
//    public void onApplicationEvent(@NonNull ContextRefreshedEvent contextRefreshedEvent) {
//        getBucketAndObject().ifPresent(this::restore);
//    }
//
//    @SneakyThrows
//    private void restore(Pair<String, String> bucketAndObject) {
//        try (InputStream objectInputStream = bucketHandler.downloadObject(bucketAndObject.getFirst(), bucketAndObject.getSecond())) {
//            Optional.of(objectInputStream)
//                    .map(inputStream -> jsonConverter.inputStreamToPojos(inputStream, NbrbDto[].class))
//                    .ifPresent(nbrbDaoRestController::saveAll);
//        }
//        log.log(INFO, "[{0}] restored from [{1}]", new Object[]{awsProperties.getDynamoDb().getObject(), bucketAndObject.toString()});
//    }
//
//    private Optional<Pair<String, String>> getBucketAndObject() {
//        return Optional.of(Pair.of(getBucket(), getObject()))
//                .filter(pair -> pair.getFirst().isPresent())
//                .filter(pair -> pair.getSecond().isPresent())
//                .map(pair -> Pair.of(pair.getFirst().get(), pair.getSecond().get()))
//                .filter(pair -> bucketHandler.isObjectExist(pair.getFirst(), pair.getSecond()));
//    }
//
//    private Optional<String> getBucket() {
//        return Optional.of(awsProperties)
//                .map(AwsProperties::getS3)
//                .map(AbstractServiceProperties::getObject);
//    }
//
//    private Optional<String> getObject() {
//        return Optional.of(awsProperties)
//                .map(AwsProperties::getDynamoDb)
//                .map(AbstractServiceProperties::getObject)
//                .map(tableName -> String.format("%s.json", tableName));
//    }
//}