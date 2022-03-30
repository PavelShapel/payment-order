package com.pavelshapel.service.location.service;

import com.pavelshapel.core.spring.boot.starter.impl.service.AbstractSpecificationDaoService;
import com.pavelshapel.core.spring.boot.starter.impl.service.decorator.DaoDecorate;
import com.pavelshapel.service.location.model.LocationType;
import com.pavelshapel.service.location.service.decorator.LocationTypeCacheableDecoratorDaoService;
import com.pavelshapel.service.location.service.decorator.LocationTypeThrowableDecoratorDaoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@DaoDecorate(decorations = {
        LocationTypeCacheableDecoratorDaoService.class,
        LocationTypeThrowableDecoratorDaoService.class})
@Transactional
public class LocationTypeDaoService extends AbstractSpecificationDaoService<Long, LocationType> {

    @Override
    public LocationType create() {
        return new LocationType();
    }
}