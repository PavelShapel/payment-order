package com.pavelshapel.aws.lambda.service.location.model.converter;

import com.pavelshapel.aws.lambda.service.location.model.Location;
import com.pavelshapel.common.module.dto.aws.LocationDto;
import com.pavelshapel.core.spring.boot.starter.api.converter.DtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.converter.ToDtoConverter;

import java.util.Optional;

@DtoConverter
public class LocationToDtoConverter implements ToDtoConverter<String, Location, LocationDto> {

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
                .ifPresent(locationDto::setParent);
    }

    private void setLocationType(LocationDto locationDto, Location location) {
        Optional.ofNullable(location.getLocationType())
                .map(Enum::name)
                .ifPresent(locationDto::setLocationType);
    }
}
