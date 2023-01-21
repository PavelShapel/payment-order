package com.pavelshapel.service.location.service.decorator;

import com.pavelshapel.jpa.spring.boot.starter.service.decorator.Decorator;
import com.pavelshapel.jpa.spring.boot.starter.service.decorator.impl.AbstractThrowableDecoratorDaoService;
import com.pavelshapel.service.location.model.Location;

@Decorator
public class LocationThrowableDecoratorDaoService extends AbstractThrowableDecoratorDaoService<Long, Location> {
}
