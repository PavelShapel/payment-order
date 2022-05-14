package com.pavelshapel.service.location.model.converter;

import com.pavelshapel.common.module.dto.jpa.LocationDto;
import com.pavelshapel.core.spring.boot.starter.api.converter.DtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.converter.ToDtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.model.Entity;
import com.pavelshapel.service.location.model.Location;

import java.util.Optional;

@DtoConverter
public class LocationToDtoConverter implements ToDtoConverter<Long, Location, LocationDto> {

    @Override
    public LocationDto convert(Location location) {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(location.getId());
        locationDto.setName(location.getName());
        setLocationType(locationDto, location);
        setParent(locationDto, location);
        return locationDto;
    }

    private void setParent(LocationDto locationDto, Location location) {
        Optional.ofNullable(location.getParent())
                .map(Location::getId)
                .ifPresent(locationDto::setParentId);
    }

    private void setLocationType(LocationDto locationDto, Location location) {
        Optional.ofNullable(location.getLocationType())
                .map(Entity::getId)
                .ifPresent(locationDto::setLocationTypeId);
    }
}
