package com.pavelshapel.aws.lambda.service.location.web;

import com.pavelshapel.aws.lambda.service.location.model.Location;
import com.pavelshapel.common.module.dto.aws.LocationDto;
import com.pavelshapel.core.spring.boot.starter.api.converter.FromDtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.converter.ToDtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.service.DaoService;
import com.pavelshapel.web.spring.boot.starter.web.AbstractDaoRestController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pavelshapel.aws.lambda.service.location.web.LocationDaoRestController.HOME_PATH;


@RestController
@RequestMapping(HOME_PATH)
public class LocationDaoRestController extends AbstractDaoRestController<String, Location, LocationDto> {
    public static final String HOME_PATH = "/locations" + StringUtils.EMPTY;

    protected LocationDaoRestController(DaoService<String, Location> locationDaoService,
                                        FromDtoConverter<String, LocationDto, Location> locationFromDtoConverter,
                                        ToDtoConverter<String, Location, LocationDto> dtoToDtoConverter) {
        super(locationDaoService, locationFromDtoConverter, dtoToDtoConverter);
    }
}
