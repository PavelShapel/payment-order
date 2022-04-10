package com.pavelshapel.aws.lambda.service.corporation.model.converter;

import com.pavelshapel.aws.lambda.service.corporation.model.Corporation;
import com.pavelshapel.common.module.dto.service.location.aws.CorporationDto;
import com.pavelshapel.core.spring.boot.starter.api.converter.DtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.converter.ToDtoConverter;

import java.util.Optional;

@DtoConverter
public class CorporationToDtoConverter implements ToDtoConverter<String, Corporation, CorporationDto> {

    @Override
    public CorporationDto convert(Corporation corporation) {
        CorporationDto corporationDto = new CorporationDto();
        corporationDto.setId(corporation.getId());
        setParent(corporationDto, corporation);
        corporationDto.setTyped(corporation.getTyped());
        return corporationDto;
    }

    private void setParent(CorporationDto corporationDto, Corporation corporation) {
        Optional.ofNullable(corporation.getParent())
                .map(Corporation::getId)
                .ifPresent(corporationDto::setParent);
    }
}
