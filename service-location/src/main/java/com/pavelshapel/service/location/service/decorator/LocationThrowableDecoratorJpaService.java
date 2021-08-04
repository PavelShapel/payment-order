package com.pavelshapel.service.location.service.decorator;

import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.Decorator;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.ThrowableDecoratorJpaService;
import com.pavelshapel.service.location.entity.Location;

@Decorator
public class LocationThrowableDecoratorJpaService extends ThrowableDecoratorJpaService<Location> {
}
