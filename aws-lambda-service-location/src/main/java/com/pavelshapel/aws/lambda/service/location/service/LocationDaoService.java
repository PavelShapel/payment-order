package com.pavelshapel.aws.lambda.service.location.service;

import com.pavelshapel.aws.lambda.service.location.model.Location;
import com.pavelshapel.aws.lambda.service.location.service.decorator.LocationCacheableDecoratorDaoService;
import com.pavelshapel.aws.lambda.service.location.service.decorator.LocationThrowableDecoratorDaoService;
import com.pavelshapel.core.spring.boot.starter.impl.service.AbstractDaoService;
import com.pavelshapel.core.spring.boot.starter.impl.service.decorator.DecorateDaoService;
import org.springframework.stereotype.Service;

@Service
@DecorateDaoService(decorations = {
        LocationCacheableDecoratorDaoService.class,
        LocationThrowableDecoratorDaoService.class})
public class LocationDaoService extends AbstractDaoService<String, Location> {
    @Override
    public Location create() {
        return new Location();
    }
}
