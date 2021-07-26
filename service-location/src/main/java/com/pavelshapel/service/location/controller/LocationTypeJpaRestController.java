package com.pavelshapel.service.location.controller;

import com.pavelshapel.jpa.spring.boot.starter.service.jpa.JpaService;
import com.pavelshapel.service.location.entity.LocationType;
import com.pavelshapel.service.location.service.LocationTypeJpaService;
import com.pavelshapel.web.spring.boot.starter.controller.AbstractJpaRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.pavelshapel.service.location.controller.LocationTypeJpaRestController.HOME_PATH;

@RestController
@RequestMapping(HOME_PATH)
public class LocationTypeJpaRestController extends AbstractJpaRestController<LocationType> {
    public static final String HOME_PATH = "/locationTypes";

    @Autowired
    protected LocationTypeJpaRestController(JpaService<LocationType> locationTypeJpaService) {
        super(locationTypeJpaService);
    }

    @GetMapping(NAME_PATH)
    public ResponseEntity<List<LocationType>> get(@PathVariable String name) {
        return ResponseEntity.ok(((LocationTypeJpaService) getJpaService()).findByNameIgnoreCaseContaining(name));
    }
}
