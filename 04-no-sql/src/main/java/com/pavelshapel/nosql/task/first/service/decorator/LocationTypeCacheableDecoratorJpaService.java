package com.pavelshapel.nosql.task.first.service.decorator;

import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.CacheableDecoratorJpaService;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.Decorator;
import com.pavelshapel.nosql.task.first.entity.LocationType;

@Decorator
public class LocationTypeCacheableDecoratorJpaService extends CacheableDecoratorJpaService<LocationType> {
    public LocationTypeCacheableDecoratorJpaService() {
        super(LocationType.class);
    }
}
