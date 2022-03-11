package com.pavelshapel.service.location.service.decorator;

import com.pavelshapel.service.location.model.Location;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.CacheableDecoratorJpaService;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.Decorator;

@Decorator
public class LocationCacheableDecoratorJpaService extends CacheableDecoratorJpaService<Long, Location> {
}
