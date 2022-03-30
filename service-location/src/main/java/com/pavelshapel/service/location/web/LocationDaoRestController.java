package com.pavelshapel.service.location.web;

import com.pavelshapel.common.module.dto.service.location.jpa.LocationDto;
import com.pavelshapel.core.spring.boot.starter.api.converter.FromDtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.converter.ToDtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.service.DaoService;
import com.pavelshapel.core.spring.boot.starter.impl.web.search.SearchSpecification;
import com.pavelshapel.service.location.model.Location;
import com.pavelshapel.web.spring.boot.starter.web.AbstractSpecificationDaoRestController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pavelshapel.service.location.web.LocationDaoRestController.HOME_PATH;


@RestController
@RequestMapping(HOME_PATH)
public class LocationDaoRestController extends AbstractSpecificationDaoRestController<Long, Location, LocationDto> {
    public static final String HOME_PATH = "/locations" + StringUtils.EMPTY;

    @Autowired
    protected LocationDaoRestController(DaoService<Long, Location> locationDaoService,
                                        FromDtoConverter<Long, LocationDto, Location> locationFromDtoConverter,
                                        ToDtoConverter<Long, Location, LocationDto> locationToDtoConverter,
                                        SearchSpecification<Location> locationSearchSpecification) {
        super(locationDaoService, locationFromDtoConverter, locationToDtoConverter, locationSearchSpecification);
    }
}
