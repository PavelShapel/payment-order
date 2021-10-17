package com.pavelshapel.nosql.task.first.service.decorator;

import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.Decorator;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.ThrowableDecoratorJpaService;
import com.pavelshapel.nosql.task.first.entity.LocationType;

@Decorator
public class LocationTypeThrowableDecoratorJpaService extends ThrowableDecoratorJpaService<LocationType> {
}
