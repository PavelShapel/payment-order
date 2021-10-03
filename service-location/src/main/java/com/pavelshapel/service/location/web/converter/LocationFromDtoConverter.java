package com.pavelshapel.service.location.web.converter;

import com.pavelshapel.common.module.dto.service.location.LocationDto;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.JpaService;
import com.pavelshapel.service.location.entity.Location;
import com.pavelshapel.service.location.entity.LocationType;
import com.pavelshapel.web.spring.boot.starter.web.converter.DtoConverter;
import com.pavelshapel.web.spring.boot.starter.web.converter.FromDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@DtoConverter
public class LocationFromDtoConverter implements FromDtoConverter<LocationDto, Location> {
    @Autowired
    private JpaService<LocationType> locationTypeJpaService;
    @Autowired
    private JpaService<Location> locationJpaService;

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
                .map(locationJpaService::findById)
                .ifPresent(location::setParent);
    }

    private void setLocationType(LocationDto locationDto, Location location) {
        Optional.ofNullable(locationDto.getLocationTypeId())
                .map(locationTypeJpaService::findById)
                .ifPresent(location::setLocationType);
    }
}
