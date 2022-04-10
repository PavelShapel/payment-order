package com.pavelshapel.aws.lambda.service.corporation.model.converter;

import com.pavelshapel.aws.lambda.service.corporation.model.Corporation;
import com.pavelshapel.aws.spring.boot.starter.converter.AbstractParentalDynamoDBTypeConverter;
import com.pavelshapel.core.spring.boot.starter.api.service.DaoService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CorporationParentalDynamoDBTypeConverter extends AbstractParentalDynamoDBTypeConverter<String, Corporation> {
    @Autowired
    public CorporationParentalDynamoDBTypeConverter(DaoService<String, Corporation> corporationDaoService) {
        super(corporationDaoService);
    }
}