package com.pavelshapel.service.location.service;

import com.pavelshapel.jpa.spring.boot.starter.service.jpa.AbstractJpaService;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.JpaDecorate;
import com.pavelshapel.service.location.entity.LocationType;
import com.pavelshapel.service.location.service.decorator.LocationTypeThrowableDecoratorJpaService;
import org.springframework.stereotype.Service;

@Service
@JpaDecorate(decorations = {LocationTypeThrowableDecoratorJpaService.class})
public class LocationTypeJpaService extends AbstractJpaService<LocationType> {

    @Override
    public LocationType create() {
        return new LocationType();
    }

    @Override
    public LocationType getParent(LocationType locationType) {
        return null;
    }
}