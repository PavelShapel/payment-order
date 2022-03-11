package com.pavelshapel.service.location.service.decorator;

import com.pavelshapel.service.location.model.LocationType;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.Decorator;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.ThrowableDecoratorJpaService;

@Decorator
public class LocationTypeThrowableDecoratorJpaService extends ThrowableDecoratorJpaService<Long, LocationType> {
}
