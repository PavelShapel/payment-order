package com.pavelshapel.service.location.service;

import com.pavelshapel.jpa.spring.boot.starter.service.jpa.AbstractJpaService;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.JpaDecorate;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.ThrowableDecoratorJpaService;
import com.pavelshapel.service.location.entity.LocationType;
import com.pavelshapel.service.location.repository.LocationTypeJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@JpaDecorate(decorations = {ThrowableDecoratorJpaService.class})
//@Loggable
public class LocationTypeJpaService extends AbstractJpaService<LocationType> {

    @Override
    public LocationType create() {
        return new LocationType();
    }

    public List<LocationType> findByNameIgnoreCaseContaining(String name) {
        return ((LocationTypeJpaRepository) getJpaRepository()).findByNameIgnoreCaseContaining(name);
    }
}