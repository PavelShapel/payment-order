package com.pavelshapel.service.location.service;

import com.pavelshapel.jpa.spring.boot.starter.repository.DaoRepository;
import com.pavelshapel.jpa.spring.boot.starter.service.AbstractDaoService;
import com.pavelshapel.jpa.spring.boot.starter.service.decorator.DecorateDaoService;
import com.pavelshapel.jpa.spring.boot.starter.service.search.AbstractSearchSpecification;
import com.pavelshapel.service.location.model.Location;
import com.pavelshapel.service.location.service.decorator.LocationCacheableDecoratorDaoService;
import com.pavelshapel.service.location.service.decorator.LocationThrowableDecoratorDaoService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Service;

@Service
@DecorateDaoService(decorations = {
        LocationCacheableDecoratorDaoService.class,
        LocationThrowableDecoratorDaoService.class})
public class LocationDaoService extends AbstractDaoService<Long, Location> {
    public LocationDaoService(DaoRepository<Long, Location> locationDaoRepository, ObjectFactory<AbstractSearchSpecification<Location>> locationSearchSpecification) {
        super(locationDaoRepository, locationSearchSpecification);
    }
}