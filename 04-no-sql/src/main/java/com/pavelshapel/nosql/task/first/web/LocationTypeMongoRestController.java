package com.pavelshapel.nosql.task.first.web;

import com.pavelshapel.core.spring.boot.starter.util.StreamUtils;
import com.pavelshapel.nosql.task.first.entity.LocationTypeMongo;
import com.pavelshapel.nosql.task.first.repository.LocationTypeMongoRepository;
import com.pavelshapel.nosql.task.first.service.ToMongoMigrator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(LocationTypeMongoRestController.HOME_PATH)
public class LocationTypeMongoRestController {
    public static final String HOME_PATH = "/locationTypes" + StringUtils.EMPTY;

    @Autowired
    private StreamUtils streamUtils;
    @Autowired
    private LocationTypeMongoRepository locationTypeMongoRepository;
    @Autowired
    private ToMongoMigrator toMongoMigrator;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LocationTypeMongo>> findAll() {
        return locationTypeMongoRepository.findAll().stream()
                .collect(streamUtils.toResponseEntityList());
    }

    @GetMapping(value = "/migrate", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> migrate() {
        toMongoMigrator.migrate();
        return ResponseEntity.ok("success");
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        locationTypeMongoRepository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
