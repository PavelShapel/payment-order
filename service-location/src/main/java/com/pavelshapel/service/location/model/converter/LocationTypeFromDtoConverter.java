package com.pavelshapel.service.location.model.converter;

import com.pavelshapel.common.module.dto.jpa.LocationTypeDto;
import com.pavelshapel.core.spring.boot.starter.api.converter.DtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.converter.FromDtoConverter;
import com.pavelshapel.service.location.model.LocationType;

@DtoConverter
public class LocationTypeFromDtoConverter implements FromDtoConverter<Long, LocationTypeDto, LocationType> {
    @Override
    public LocationType convert(LocationTypeDto locationTypeDto) {
        LocationType locationType = new LocationType();
        locationType.setId(locationTypeDto.getId());
        locationType.setName(locationTypeDto.getName());
        return locationType;
    }
}
