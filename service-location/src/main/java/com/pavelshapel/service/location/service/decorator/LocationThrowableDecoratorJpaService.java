package com.pavelshapel.service.location.service.decorator;

import com.pavelshapel.service.location.entity.Location;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.Decorator;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.ThrowableDecoratorJpaService;

@Decorator
public class LocationThrowableDecoratorJpaService extends ThrowableDecoratorJpaService<Location> {
}
