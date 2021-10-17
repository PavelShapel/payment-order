package com.pavelshapel.nosql.task.first.service;

import com.pavelshapel.jpa.spring.boot.starter.service.jpa.JpaService;
import com.pavelshapel.nosql.task.first.entity.LocationType;
import com.pavelshapel.nosql.task.first.repository.LocationTypeMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToMongoMigrator implements Migrator {
    @Autowired
    private ToLocationMongoConverter toLocationMongoConverter;
    @Autowired
    private JpaService<LocationType> locationTypeJpaService;
    @Autowired
    private LocationTypeMongoRepository locationTypeMongoRepository;

    public void migrate() {
        locationTypeJpaService.findAll().stream()
                .map(location -> toLocationMongoConverter.convert(location))
                .forEach(locationTypeMongo -> locationTypeMongoRepository.save(locationTypeMongo));
    }
}
