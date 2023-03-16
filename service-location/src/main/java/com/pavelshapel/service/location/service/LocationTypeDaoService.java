package com.pavelshapel.service.location.service;

import com.pavelshapel.jpa.spring.boot.starter.repository.DaoRepository;
import com.pavelshapel.jpa.spring.boot.starter.service.AbstractDaoService;
import com.pavelshapel.jpa.spring.boot.starter.service.decorator.DecorateDaoService;
import com.pavelshapel.jpa.spring.boot.starter.service.search.AbstractSearchSpecification;
import com.pavelshapel.service.location.model.LocationType;
import com.pavelshapel.service.location.service.decorator.LocationTypeCacheableDecoratorDaoService;
import com.pavelshapel.service.location.service.decorator.LocationTypeThrowableDecoratorDaoService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Service;

@Service
@DecorateDaoService(decorations = {
        LocationTypeCacheableDecoratorDaoService.class,
        LocationTypeThrowableDecoratorDaoService.class})
public class LocationTypeDaoService extends AbstractDaoService<Long, LocationType> {
    public LocationTypeDaoService(DaoRepository<Long, LocationType> locationTypeDaoRepository, ObjectFactory<AbstractSearchSpecification<LocationType>> locationTypeSearchSpecification) {
        super(locationTypeDaoRepository, locationTypeSearchSpecification);
    }
}