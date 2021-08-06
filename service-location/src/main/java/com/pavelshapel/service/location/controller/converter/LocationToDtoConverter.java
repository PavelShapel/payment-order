package com.pavelshapel.service.location.controller.converter;

import com.pavelshapel.jpa.spring.boot.starter.service.jpa.JpaService;
import com.pavelshapel.service.location.entity.Location;
import com.pavelshapel.service.location.entity.LocationType;
import com.pavelshapel.web.spring.boot.starter.controller.converter.ToDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LocationToDtoConverter extends ToDtoConverter<Location> {
    @Autowired
    private JpaService<LocationType> locationTypeJpaService;

    @Override
    public Location convert(Location location) {
        Optional.ofNullable(location.getLocationType())
                .ifPresent(locationType -> location.setLocationTypeId(locationType.getId()));
        Optional.ofNullable(location.getParent())
                .ifPresent(parent -> location.setParentId(parent.getId()));
        return location;
    }
}
