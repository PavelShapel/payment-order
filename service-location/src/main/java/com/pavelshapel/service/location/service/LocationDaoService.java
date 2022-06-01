package com.pavelshapel.service.location.service;

import com.pavelshapel.jpa.spring.boot.starter.service.AbstractSpecificationDaoService;
import com.pavelshapel.jpa.spring.boot.starter.service.decorator.DecorateDaoService;
import com.pavelshapel.service.location.model.Location;
import com.pavelshapel.service.location.service.decorator.LocationCacheableDecoratorDaoService;
import com.pavelshapel.service.location.service.decorator.LocationThrowableDecoratorDaoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@DecorateDaoService(decorations = {
        LocationCacheableDecoratorDaoService.class,
        LocationThrowableDecoratorDaoService.class})
@Transactional
public class LocationDaoService extends AbstractSpecificationDaoService<Long, Location> {
    @Override
    public Location create() {
        return new Location();
    }
}