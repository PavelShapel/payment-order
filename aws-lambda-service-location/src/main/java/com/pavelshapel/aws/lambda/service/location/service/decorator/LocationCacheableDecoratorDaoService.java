package com.pavelshapel.aws.lambda.service.location.service.decorator;

import com.pavelshapel.aws.lambda.service.location.model.Location;
import com.pavelshapel.jpa.spring.boot.starter.service.decorator.Decorator;
import com.pavelshapel.jpa.spring.boot.starter.service.decorator.instance.AbstractCacheableDecoratorDaoService;

@Decorator
public class LocationCacheableDecoratorDaoService extends AbstractCacheableDecoratorDaoService<String, Location> {
}
