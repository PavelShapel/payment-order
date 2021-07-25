package com.pavelshapel.service.location.service;

import com.pavelshapel.jpa.spring.boot.starter.service.jpa.AbstractJpaService;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.JpaDecorate;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.decorator.ThrowableDecoratorJpaService;
import com.pavelshapel.service.location.entity.Location;
import com.pavelshapel.service.location.repository.LocationJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@JpaDecorate(decorations = {ThrowableDecoratorJpaService.class})
public class LocationJpaService extends AbstractJpaService<Location> {

    @Override
    public Location create() {
        return new Location();
    }


    public List<Location> findByNameIgnoreCaseContaining(String name) {
        return ((LocationJpaRepository) getJpaRepository()).findByNameIgnoreCaseContaining(name);
    }
}