package com.pavelshapel.nosql.task.first.service;

import com.pavelshapel.nosql.task.first.entity.LocationType;
import com.pavelshapel.nosql.task.first.entity.LocationTypeMongo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class ToLocationMongoConverter implements Converter<LocationType, LocationTypeMongo> {
    @Override
    public LocationTypeMongo convert(LocationType location) {
        LocationTypeMongo locationTypeMongo = new LocationTypeMongo();
        locationTypeMongo.setId(location.getId());
        locationTypeMongo.setName(location.getName());
        return locationTypeMongo;
    }
}
