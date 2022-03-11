package com.pavelshapel.service.location.web;

import com.pavelshapel.common.module.dto.service.location.LocationTypeDto;
import com.pavelshapel.service.location.model.LocationType;
import com.pavelshapel.jpa.spring.boot.starter.repository.search.SearchSpecification;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.JpaService;
import com.pavelshapel.web.spring.boot.starter.web.AbstractJpaRestController;
import com.pavelshapel.web.spring.boot.starter.web.converter.FromDtoConverter;
import com.pavelshapel.web.spring.boot.starter.web.converter.ToDtoConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pavelshapel.service.location.web.LocationTypeJpaRestController.HOME_PATH;

@RestController
@RequestMapping(HOME_PATH)
public class LocationTypeJpaRestController extends AbstractJpaRestController<Long, LocationType, LocationTypeDto> {
    public static final String HOME_PATH = "/locationTypes" + StringUtils.EMPTY;

    @Autowired
    public LocationTypeJpaRestController(JpaService<Long, LocationType> locationTypeJpaService,
                                         SearchSpecification<LocationType> locationTypeSearchSpecification,
                                         FromDtoConverter<Long, LocationTypeDto, LocationType> locationTypeFromDtoConverter,
                                         ToDtoConverter<Long, LocationType, LocationTypeDto> locationTypeToDtoConverter) {
        super(locationTypeJpaService, locationTypeSearchSpecification, locationTypeFromDtoConverter, locationTypeToDtoConverter);
    }
}
