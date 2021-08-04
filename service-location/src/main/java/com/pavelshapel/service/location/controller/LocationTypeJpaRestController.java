package com.pavelshapel.service.location.controller;

import com.pavelshapel.jpa.spring.boot.starter.repository.search.SearchSpecification;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.JpaService;
import com.pavelshapel.service.location.entity.LocationType;
import com.pavelshapel.web.spring.boot.starter.controller.AbstractJpaRestController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pavelshapel.service.location.controller.LocationTypeJpaRestController.HOME_PATH;

@RestController
@RequestMapping(HOME_PATH)
public class LocationTypeJpaRestController extends AbstractJpaRestController<LocationType> {
    public static final String HOME_PATH = "/locationTypes" + StringUtils.EMPTY;

    @Autowired
    public LocationTypeJpaRestController(JpaService<LocationType> locationTypeJpaService, SearchSpecification<LocationType> locationTypeSearchSpecification) {
        super(locationTypeJpaService, locationTypeSearchSpecification);
    }
}
