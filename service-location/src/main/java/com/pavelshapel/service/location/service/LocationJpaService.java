package com.pavelshapel.service.location.service;

import com.pavelshapel.jpa.spring.boot.starter.repository.AbstractJpaRepository;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.AbstractJpaService;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.JpaDecorate;
import com.pavelshapel.service.location.entity.Location;
import com.pavelshapel.service.location.service.decorator.LocationThrowableDecoratorJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@JpaDecorate(decorations = {LocationThrowableDecoratorJpaService.class})
public class LocationJpaService extends AbstractJpaService<Location> {

    @Autowired
    public LocationJpaService(AbstractJpaRepository<Location> abstractJpaRepository) {
        super(abstractJpaRepository);
    }

    @Override
    public Location create() {
        return new Location();
    }

    @Override
    public Location getParent(Location location) {
        return location.getParent();
    }
}