package com.pavelshapel.service.location.entity.converter;

import com.pavelshapel.jpa.spring.boot.starter.service.jpa.JpaService;
import com.pavelshapel.service.location.entity.Location;
import com.pavelshapel.service.location.entity.LocationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LocationFromDtoConverter implements Converter<Location, Location> {
    @Autowired
    private JpaService<LocationType> locationTypeJpaService;
    @Autowired
    private JpaService<Location> locationJpaService;

    @Override
    public Location convert(Location location) {
        setLocationType(location);
        setParent(location);
        return location;
    }

    private void setParent(Location location) {
        Optional.ofNullable(location.getParentId())
                .ifPresent(parentId -> {
                    Location parent = locationJpaService.findById(parentId);
                    location.setParent(parent);
                });
    }

    private void setLocationType(Location location) {
        Optional.ofNullable(location.getLocationTypeId())
                .ifPresent(locationTypeId -> {
                    LocationType locationType = locationTypeJpaService.findById(locationTypeId);
                    location.setLocationType(locationType);
                });
    }
}
