package com.pavelshapel.service.location.service;

import com.pavelshapel.jpa.spring.boot.starter.service.AbstractSpecificationDaoService;
import com.pavelshapel.jpa.spring.boot.starter.service.decorator.DecorateDaoService;
import com.pavelshapel.service.location.model.LocationType;
import com.pavelshapel.service.location.service.decorator.LocationTypeCacheableDecoratorDaoService;
import com.pavelshapel.service.location.service.decorator.LocationTypeThrowableDecoratorDaoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@DecorateDaoService(decorations = {
        LocationTypeCacheableDecoratorDaoService.class,
        LocationTypeThrowableDecoratorDaoService.class})
@Transactional
public class LocationTypeDaoService extends AbstractSpecificationDaoService<Long, LocationType> {

    @Override
    public LocationType create() {
        return new LocationType();
    }
}