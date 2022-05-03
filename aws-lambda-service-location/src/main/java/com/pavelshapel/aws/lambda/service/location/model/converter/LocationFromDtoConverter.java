package com.pavelshapel.aws.lambda.service.location.model.converter;

import com.pavelshapel.aws.lambda.service.location.model.Location;
import com.pavelshapel.aws.lambda.service.location.model.LocationType;
import com.pavelshapel.common.module.dto.aws.LocationDto;
import com.pavelshapel.core.spring.boot.starter.api.converter.DtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.converter.FromDtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.service.DaoService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@DtoConverter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class LocationFromDtoConverter implements FromDtoConverter<String, LocationDto, Location> {
    DaoService<String, Location> locationDaoService;

    @Override
    public Location convert(LocationDto locationDto) {
        Location location = new Location();
        location.setId(locationDto.getId());
        location.setName(locationDto.getName());
        setLocationType(locationDto, location);
        setParent(locationDto, location);
        return location;
    }

    private void setParent(LocationDto locationDto, Location location) {
        Optional.ofNullable(locationDto.getParent())
                .map(locationDaoService::findById)
                .ifPresent(location::setParent);
    }

    private void setLocationType(LocationDto locationDto, Location location) {
        Optional.ofNullable(locationDto.getLocationType())
                .map(LocationType::valueOf)
                .ifPresent(location::setLocationType);
    }
}
