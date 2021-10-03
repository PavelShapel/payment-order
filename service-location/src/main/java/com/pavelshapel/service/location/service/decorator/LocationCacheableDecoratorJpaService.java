package com.pavelshapel.service.location.service.decorator;

import com.pavelshapel.service.location.entity.Location;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.CacheableDecoratorJpaService;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.Decorator;

@Decorator
public class LocationCacheableDecoratorJpaService extends CacheableDecoratorJpaService<Location> {
    public LocationCacheableDecoratorJpaService() {
        super(Location.class);
    }
}
