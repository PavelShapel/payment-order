package com.pavelshapel.service.location.web;

import com.pavelshapel.common.module.dto.service.location.LocationDto;
import com.pavelshapel.service.location.entity.Location;
import com.pavelshapel.jpa.spring.boot.starter.repository.search.SearchSpecification;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.JpaService;
import com.pavelshapel.web.spring.boot.starter.web.AbstractJpaRestController;
import com.pavelshapel.web.spring.boot.starter.web.converter.FromDtoConverter;
import com.pavelshapel.web.spring.boot.starter.web.converter.ToDtoConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pavelshapel.service.location.web.LocationJpaRestController.HOME_PATH;


@RestController
@RequestMapping(HOME_PATH)
public class LocationJpaRestController extends AbstractJpaRestController<Location, LocationDto> {
    public static final String HOME_PATH = "/locations" + StringUtils.EMPTY;

    @Autowired
    public LocationJpaRestController(JpaService<Location> locationJpaService,
                                     SearchSpecification<Location> locationSearchSpecification,
                                     FromDtoConverter<LocationDto, Location> locationFromDtoConverter,
                                     ToDtoConverter<Location, LocationDto> locationToDtoConverter) {
        super(locationJpaService, locationSearchSpecification, locationFromDtoConverter, locationToDtoConverter);
    }
}
