package com.pavelshapel.service.location.service;

import com.pavelshapel.jpa.spring.boot.starter.service.jpa.AbstractJpaService;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.JpaDecorate;
import com.pavelshapel.service.location.entity.Location;
import com.pavelshapel.service.location.service.decorator.LocationCacheableDecoratorJpaService;
import com.pavelshapel.service.location.service.decorator.LocationThrowableDecoratorJpaService;
import org.springframework.stereotype.Service;

@Service
@JpaDecorate(decorations = {
        LocationCacheableDecoratorJpaService.class,
        LocationThrowableDecoratorJpaService.class}
)
public class LocationJpaService extends AbstractJpaService<Location> {

    public LocationJpaService() {
        super(Location.class);
    }

    @Override
    public Location create() {
        return new Location();
    }

    @Override
    public Location getParent(Location location) {
        return location.getParent();
    }
}