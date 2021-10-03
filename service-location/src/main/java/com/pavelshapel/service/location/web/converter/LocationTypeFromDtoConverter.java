package com.pavelshapel.service.location.web.converter;

import com.pavelshapel.common.module.dto.service.location.LocationTypeDto;
import com.pavelshapel.service.location.entity.LocationType;
import com.pavelshapel.web.spring.boot.starter.web.converter.DtoConverter;
import com.pavelshapel.web.spring.boot.starter.web.converter.FromDtoConverter;

@DtoConverter
public class LocationTypeFromDtoConverter implements FromDtoConverter<LocationTypeDto, LocationType> {
    @Override
    public LocationType convert(LocationTypeDto locationTypeDto) {
        LocationType locationType = new LocationType();
        locationType.setId(locationTypeDto.getId());
        locationType.setName(locationTypeDto.getName());
        return locationType;
    }
}
