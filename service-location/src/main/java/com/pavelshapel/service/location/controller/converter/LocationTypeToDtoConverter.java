package com.pavelshapel.service.location.controller.converter;

import com.pavelshapel.service.location.entity.LocationType;
import com.pavelshapel.web.spring.boot.starter.controller.converter.ToDtoConverter;
import org.springframework.stereotype.Component;

@Component
public class LocationTypeToDtoConverter extends ToDtoConverter<LocationType> {
}
