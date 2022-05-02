package com.pavelshapel.aws.lambda.service.corporation.web;

import com.pavelshapel.aws.lambda.service.corporation.model.Corporation;
import com.pavelshapel.common.module.dto.aws.CorporationDto;
import com.pavelshapel.core.spring.boot.starter.api.converter.FromDtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.converter.ToDtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.service.DaoService;
import com.pavelshapel.web.spring.boot.starter.web.AbstractDaoRestController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pavelshapel.aws.lambda.service.corporation.web.CorporationDaoRestController.HOME_PATH;


@RestController
@RequestMapping(HOME_PATH)
public class CorporationDaoRestController extends AbstractDaoRestController<String, Corporation, CorporationDto> {
    public static final String HOME_PATH = "/corporations" + StringUtils.EMPTY;

    protected CorporationDaoRestController(DaoService<String, Corporation> corporationDaoService,
                                           FromDtoConverter<String, CorporationDto, Corporation> corporationFromDtoConverter,
                                           ToDtoConverter<String, Corporation, CorporationDto> corporationToDtoConverter) {
        super(corporationDaoService, corporationFromDtoConverter, corporationToDtoConverter);
    }
}
