package com.pavelshapel.service.location.service.decorator;

import com.pavelshapel.jpa.spring.boot.starter.service.decorator.Decorator;
import com.pavelshapel.jpa.spring.boot.starter.service.decorator.impl.AbstractCacheableDecoratorDaoService;
import com.pavelshapel.service.location.model.Location;

@Decorator
public class LocationCacheableDecoratorDaoService extends AbstractCacheableDecoratorDaoService<Long, Location> {
}
