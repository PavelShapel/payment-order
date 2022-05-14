package com.pavelshapel.service.location.web;

import com.pavelshapel.common.module.dto.jpa.LocationTypeDto;
import com.pavelshapel.core.spring.boot.starter.api.converter.FromDtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.converter.ToDtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.service.DaoService;
import com.pavelshapel.core.spring.boot.starter.impl.web.search.AbstractSearchSpecification;
import com.pavelshapel.service.location.model.LocationType;
import com.pavelshapel.web.spring.boot.starter.web.AbstractSpecificationDaoRestController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pavelshapel.service.location.web.LocationTypeDaoRestController.HOME_PATH;

@RestController
@RequestMapping(HOME_PATH)
public class LocationTypeDaoRestController extends AbstractSpecificationDaoRestController<Long, LocationType, LocationTypeDto> {
    public static final String HOME_PATH = "/locationTypes" + StringUtils.EMPTY;

    @Autowired
    protected LocationTypeDaoRestController(DaoService<Long, LocationType> locationTypeDaoService,
                                            FromDtoConverter<Long, LocationTypeDto, LocationType> locationTypeFromDtoConverter,
                                            ToDtoConverter<Long, LocationType, LocationTypeDto> locationTypeToDtoConverter,
                                            ObjectFactory<AbstractSearchSpecification<LocationType>> locationTypeSearchSpecificationFactory) {
        super(locationTypeDaoService, locationTypeFromDtoConverter, locationTypeToDtoConverter, locationTypeSearchSpecificationFactory);
    }
}
