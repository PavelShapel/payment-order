package com.pavelshapel.service.location.service;

import com.pavelshapel.core.spring.boot.starter.impl.service.AbstractSpecificationDaoService;
import com.pavelshapel.core.spring.boot.starter.impl.service.decorator.DaoDecorate;
import com.pavelshapel.service.location.model.Location;
import com.pavelshapel.service.location.service.decorator.LocationCacheableDecoratorDaoService;
import com.pavelshapel.service.location.service.decorator.LocationThrowableDecoratorDaoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@DaoDecorate(decorations = {
        LocationCacheableDecoratorDaoService.class,
        LocationThrowableDecoratorDaoService.class})
@Transactional
public class LocationDaoService extends AbstractSpecificationDaoService<Long, Location> {
    @Override
    public Location create() {
        return new Location();
    }
}