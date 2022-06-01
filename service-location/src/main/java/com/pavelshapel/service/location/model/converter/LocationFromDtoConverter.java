package com.pavelshapel.service.location.model.converter;

import com.pavelshapel.common.module.dto.jpa.LocationDto;
import com.pavelshapel.core.spring.boot.starter.api.converter.DtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.converter.FromDtoConverter;
import com.pavelshapel.jpa.spring.boot.starter.service.DaoService;
import com.pavelshapel.service.location.model.Location;
import com.pavelshapel.service.location.model.LocationType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@DtoConverter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class LocationFromDtoConverter implements FromDtoConverter<Long, LocationDto, Location> {
    DaoService<Long, LocationType> locationTypeDaoService;
    DaoService<Long, Location> locationDaoService;

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
        Optional.ofNullable(locationDto.getParentId())
                .map(locationDaoService::findById)
                .ifPresent(location::setParent);
    }

    private void setLocationType(LocationDto locationDto, Location location) {
        Optional.ofNullable(locationDto.getLocationTypeId())
                .map(locationTypeDaoService::findById)
                .ifPresent(location::setLocationType);
    }
}
