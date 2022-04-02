package com.pavelshapel.aws.lambda.service.location.service.decorator;

import com.pavelshapel.aws.lambda.service.location.model.Location;
import com.pavelshapel.core.spring.boot.starter.api.service.decorator.Decorator;
import com.pavelshapel.core.spring.boot.starter.impl.service.decorator.instance.AbstractThrowableDecoratorDaoService;

@Decorator
public class LocationThrowableDecoratorDaoService extends AbstractThrowableDecoratorDaoService<String, Location> {
}
