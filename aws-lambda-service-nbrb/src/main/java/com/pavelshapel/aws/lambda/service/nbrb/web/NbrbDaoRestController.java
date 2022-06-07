package com.pavelshapel.aws.lambda.service.nbrb.web;

import com.pavelshapel.aws.lambda.service.nbrb.model.Nbrb;
import com.pavelshapel.common.module.dto.aws.NbrbDto;
import com.pavelshapel.core.spring.boot.starter.api.converter.FromDtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.converter.ToDtoConverter;
import com.pavelshapel.jpa.spring.boot.starter.service.DaoService;
import com.pavelshapel.web.spring.boot.starter.web.AbstractDaoRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pavelshapel.aws.lambda.service.nbrb.model.Nbrb.TABLE_NAME;
import static com.pavelshapel.aws.lambda.service.nbrb.web.NbrbDaoRestController.HOME_PATH;


@RestController
@RequestMapping(HOME_PATH)
public class NbrbDaoRestController extends AbstractDaoRestController<String, Nbrb, NbrbDto> {
    public static final String HOME_PATH = TABLE_NAME + "s";

    protected NbrbDaoRestController(DaoService<String, Nbrb> nbrbDaoService,
                                    FromDtoConverter<String, NbrbDto, Nbrb> nbrbFromDtoConverter,
                                    ToDtoConverter<String, Nbrb, NbrbDto> nbrbToDtoConverter) {
        super(nbrbDaoService, nbrbFromDtoConverter, nbrbToDtoConverter);
    }
}
