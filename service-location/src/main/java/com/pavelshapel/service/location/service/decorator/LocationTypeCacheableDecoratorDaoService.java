package com.pavelshapel.service.location.service.decorator;

import com.pavelshapel.core.spring.boot.starter.api.service.decorator.Decorator;
import com.pavelshapel.core.spring.boot.starter.impl.service.decorator.instance.AbstractCacheableDecoratorDaoService;
import com.pavelshapel.service.location.model.LocationType;

@Decorator
public class LocationTypeCacheableDecoratorDaoService extends AbstractCacheableDecoratorDaoService<Long, LocationType> {
}
