package com.pavelshapel.service.location.web.converter;

import com.pavelshapel.common.module.dto.service.location.LocationDto;
import com.pavelshapel.jpa.spring.boot.starter.entity.AbstractEntity;
import com.pavelshapel.service.location.entity.Location;
import com.pavelshapel.web.spring.boot.starter.web.converter.DtoConverter;
import com.pavelshapel.web.spring.boot.starter.web.converter.ToDtoConverter;

import java.util.Optional;

@DtoConverter
public class LocationToDtoConverter implements ToDtoConverter<Location, LocationDto> {

    @Override
    public LocationDto convert(Location location) {
        Long locationTypeId = Optional.ofNullable(location.getLocationType())
                .map(AbstractEntity::getId)
                .orElse(null);
        Long parentId = Optional.ofNullable(location.getParent())
                .map(Location::getId)
                .orElse(null);
        LocationDto locationDto = new LocationDto();
        locationDto.setId(location.getId());
        locationDto.setName(location.getName());
        locationDto.setLocationTypeId(locationTypeId);
        locationDto.setParentId(parentId);
        return locationDto;
    }
}
