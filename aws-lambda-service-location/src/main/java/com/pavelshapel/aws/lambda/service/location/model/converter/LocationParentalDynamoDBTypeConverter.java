package com.pavelshapel.aws.lambda.service.location.model.converter;

import com.pavelshapel.aws.lambda.service.location.model.Location;
import com.pavelshapel.aws.spring.boot.starter.converter.AbstractParentalDynamoDBTypeConverter;
import com.pavelshapel.core.spring.boot.starter.api.service.DaoService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class LocationParentalDynamoDBTypeConverter extends AbstractParentalDynamoDBTypeConverter<String, Location> {
    @Autowired
    public LocationParentalDynamoDBTypeConverter(DaoService<String, Location> locationDaoService) {
        super(locationDaoService);
    }
}