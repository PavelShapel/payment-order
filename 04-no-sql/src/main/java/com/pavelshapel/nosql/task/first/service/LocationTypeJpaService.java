package com.pavelshapel.nosql.task.first.service;

import com.pavelshapel.jpa.spring.boot.starter.service.jpa.AbstractJpaService;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.JpaDecorate;
import com.pavelshapel.nosql.task.first.entity.LocationType;
import com.pavelshapel.nosql.task.first.service.decorator.LocationTypeCacheableDecoratorJpaService;
import com.pavelshapel.nosql.task.first.service.decorator.LocationTypeThrowableDecoratorJpaService;
import org.springframework.stereotype.Service;

@Service
@JpaDecorate(decorations = {
        LocationTypeCacheableDecoratorJpaService.class,
        LocationTypeThrowableDecoratorJpaService.class}
)
public class LocationTypeJpaService extends AbstractJpaService<LocationType> {

    public LocationTypeJpaService() {
        super(LocationType.class);
    }

    @Override
    public LocationType create() {
        return new LocationType();
    }

    @Override
    public LocationType getParent(LocationType locationType) {
        return null;
    }
}