package com.pavelshapel.aws.sqs.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Map;

public class JsonParser {
    private ObjectMapper objectMapper;

    public JsonParser() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    }

    @SneakyThrows
    public String pojoToJson(Object object) {
        return objectMapper.writeValueAsString(object);
    }

    @SneakyThrows
    public  <T> T jsonToPojo(String json, Class<? extends T> targetClass) {
        return objectMapper.readValue(json, targetClass);
    }

    @SneakyThrows
    public Map jsonToMap(String json) {
        return objectMapper.readValue(json, Map.class);
    }
}
