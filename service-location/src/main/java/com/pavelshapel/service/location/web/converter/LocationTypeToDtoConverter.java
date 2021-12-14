package com.pavelshapel.service.location.web.converter;

import com.pavelshapel.common.module.dto.service.location.LocationTypeDto;
import com.pavelshapel.service.location.entity.LocationType;
import com.pavelshapel.web.spring.boot.starter.web.converter.DtoConverter;
import com.pavelshapel.web.spring.boot.starter.web.converter.ToDtoConverter;

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
