package com.pavelshapel.service.location.service;

import com.pavelshapel.jpa.spring.boot.starter.service.jpa.AbstractJpaService;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.JpaDecorate;
import com.pavelshapel.service.location.model.LocationType;
import com.pavelshapel.service.location.service.decorator.LocationTypeCacheableDecoratorJpaService;
import com.pavelshapel.service.location.service.decorator.LocationTypeThrowableDecoratorJpaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@JpaDecorate(decorations = {
        LocationTypeCacheableDecoratorJpaService.class,
        LocationTypeThrowableDecoratorJpaService.class}
)
@Transactional
public class LocationTypeJpaService extends AbstractJpaService<Long, LocationType> {

    @Override
    public LocationType create() {
        return new LocationType();
    }

    @Override
    public LocationType getParent(LocationType locationType) {
        return null;
    }
}