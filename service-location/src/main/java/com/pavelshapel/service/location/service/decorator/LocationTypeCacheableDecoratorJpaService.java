package com.pavelshapel.service.location.service.decorator;

import com.pavelshapel.service.location.entity.LocationType;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.CacheableDecoratorJpaService;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.Decorator;

@Decorator
public class LocationTypeCacheableDecoratorJpaService extends CacheableDecoratorJpaService<Long, LocationType> {
}
