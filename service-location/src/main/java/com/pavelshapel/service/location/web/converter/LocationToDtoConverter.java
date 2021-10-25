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
                .map(AbstractEntity::getId)
                .ifPresent(locationDto::setLocationTypeId);
    }
}
