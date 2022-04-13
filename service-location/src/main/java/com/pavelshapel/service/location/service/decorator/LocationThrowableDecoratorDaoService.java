package com.pavelshapel.service.location.service.decorator;

import com.pavelshapel.core.spring.boot.starter.impl.service.decorator.Decorator;
import com.pavelshapel.core.spring.boot.starter.impl.service.decorator.instance.AbstractThrowableDecoratorDaoService;
import com.pavelshapel.service.location.model.Location;

@Decorator
public class LocationThrowableDecoratorDaoService extends AbstractThrowableDecoratorDaoService<Long, Location> {
}
