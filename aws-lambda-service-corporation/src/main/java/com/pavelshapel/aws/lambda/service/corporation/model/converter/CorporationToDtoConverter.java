package com.pavelshapel.aws.lambda.service.corporation.model.converter;

import com.pavelshapel.aws.lambda.service.corporation.model.Corporation;
import com.pavelshapel.common.module.dto.aws.CorporationDto;
import com.pavelshapel.core.spring.boot.starter.api.converter.DtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.converter.ToDtoConverter;
import com.pavelshapel.core.spring.boot.starter.impl.model.TypedDto;
import com.pavelshapel.json.spring.boot.starter.converter.JsonConverter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@DtoConverter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CorporationToDtoConverter implements ToDtoConverter<String, Corporation, CorporationDto> {
    @Autowired
    JsonConverter jsonConverter;

    @Override
    public CorporationDto convert(Corporation corporation) {
        CorporationDto corporationDto = new CorporationDto();
        corporationDto.setId(corporation.getId());
        setParent(corporationDto, corporation);
        setTyped(corporationDto, corporation);
        return corporationDto;
    }

    private void setParent(CorporationDto corporationDto, Corporation corporation) {
        Optional.ofNullable(corporation)
                .map(Corporation::getParent)
                .map(Corporation::getId)
                .ifPresent(corporationDto::setParent);
    }

    private void setTyped(CorporationDto corporationDto, Corporation corporation) {
        Optional.ofNullable(corporation)
                .map(Corporation::getTyped)
                .map(jsonConverter::pojoToMap)
                .map(TypedDto::new)
                .ifPresent(corporationDto::setTyped);
    }
}
