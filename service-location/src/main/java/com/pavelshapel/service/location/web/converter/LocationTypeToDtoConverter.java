package com.pavelshapel.service.location.web.converter;

import com.pavelshapel.common.module.dto.service.location.jpa.LocationTypeDto;
import com.pavelshapel.core.spring.boot.starter.api.converter.DtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.converter.ToDtoConverter;
import com.pavelshapel.service.location.model.LocationType;

@DtoConverter
public class LocationTypeToDtoConverter implements ToDtoConverter<Long, LocationType, LocationTypeDto> {
    @Override
    public LocationTypeDto convert(LocationType locationType) {
        LocationTypeDto locationTypeDto = new LocationTypeDto();
        locationTypeDto.setId(locationType.getId());
        locationTypeDto.setName(locationType.getName());
        return locationTypeDto;
    }
}
