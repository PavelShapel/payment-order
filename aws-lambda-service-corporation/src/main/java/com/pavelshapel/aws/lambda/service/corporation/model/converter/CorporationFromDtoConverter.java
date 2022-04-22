package com.pavelshapel.aws.lambda.service.corporation.model.converter;

import com.pavelshapel.aws.lambda.service.corporation.handler.Handler;
import com.pavelshapel.aws.lambda.service.corporation.model.Corporation;
import com.pavelshapel.common.module.dto.service.location.aws.CorporationDto;
import com.pavelshapel.core.spring.boot.starter.api.converter.DtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.converter.FromDtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.service.DaoService;
import com.pavelshapel.core.spring.boot.starter.impl.model.AbstractParentalDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@DtoConverter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CorporationFromDtoConverter implements FromDtoConverter<String, CorporationDto, Corporation> {
    DaoService<String, Corporation> corporationDaoService;
    Handler typedHandler;

    @Override
    public Corporation convert(CorporationDto corporationDto) {
        Corporation corporation = new Corporation();
        corporation.setId(corporationDto.getId());
        setParent(corporationDto, corporation);
        setTyped(corporationDto, corporation);
        return corporation;
    }

    private void setParent(CorporationDto corporationDto, Corporation corporation) {
        Optional.ofNullable(corporationDto)
                .map(AbstractParentalDto::getParent)
                .map(corporationDaoService::findById)
                .ifPresent(corporation::setParent);
    }

    private void setTyped(CorporationDto corporationDto, Corporation corporation) {
        Optional.ofNullable(corporationDto)
                .map(CorporationDto::getTyped)
                .flatMap(typedHandler::getTyped)
                .ifPresent(corporation::setTyped);
    }
}
