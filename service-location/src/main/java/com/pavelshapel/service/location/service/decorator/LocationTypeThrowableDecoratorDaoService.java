package com.pavelshapel.service.location.service.decorator;

import com.pavelshapel.jpa.spring.boot.starter.service.decorator.Decorator;
import com.pavelshapel.jpa.spring.boot.starter.service.decorator.instance.AbstractThrowableDecoratorDaoService;
import com.pavelshapel.service.location.model.LocationType;

@Decorator
public class LocationTypeThrowableDecoratorDaoService extends AbstractThrowableDecoratorDaoService<Long, LocationType> {
}
