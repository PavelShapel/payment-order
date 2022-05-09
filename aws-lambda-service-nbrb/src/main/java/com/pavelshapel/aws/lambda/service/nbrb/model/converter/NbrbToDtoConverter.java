package com.pavelshapel.aws.lambda.service.nbrb.model.converter;

import com.pavelshapel.aws.lambda.service.nbrb.model.Nbrb;
import com.pavelshapel.common.module.dto.aws.NbrbDto;
import com.pavelshapel.core.spring.boot.starter.api.converter.DtoConverter;
import com.pavelshapel.core.spring.boot.starter.impl.converter.AbstractToDtoConverter;

@DtoConverter
public class NbrbToDtoConverter extends AbstractToDtoConverter<String, Nbrb, NbrbDto> {
}
