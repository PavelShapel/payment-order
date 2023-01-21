package com.pavelshapel.aws.lambda.service.location.service.decorator;

import com.pavelshapel.aws.lambda.service.location.model.Location;
import com.pavelshapel.jpa.spring.boot.starter.service.decorator.Decorator;
import com.pavelshapel.jpa.spring.boot.starter.service.decorator.instance.AbstractThrowableDecoratorDaoService;

@Decorator
public class LocationThrowableDecoratorDaoService extends AbstractThrowableDecoratorDaoService<String, Location> {
}
