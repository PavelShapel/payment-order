package com.pavelshapel.aws.lambda.service.corporation.model.converter;

import com.pavelshapel.aws.lambda.service.corporation.model.Corporation;
import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.common.module.dto.service.location.aws.CorporationDto;
import com.pavelshapel.core.spring.boot.starter.api.bean.BeansCollection;
import com.pavelshapel.core.spring.boot.starter.api.converter.DtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.converter.FromDtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;
import com.pavelshapel.core.spring.boot.starter.api.service.DaoService;
import com.pavelshapel.core.spring.boot.starter.impl.model.AbstractParentalDto;
import com.pavelshapel.json.spring.boot.starter.converter.JsonConverter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@DtoConverter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CorporationFromDtoConverter extends AbstractFromDtoConverter implements FromDtoConverter<String, CorporationDto, Corporation> {
    DaoService<String, Corporation> corporationDaoService;
    JsonConverter jacksonJsonConverter;
    BeansCollection<Typed<Type>> typedBeansCollection;

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
                .flatMap(typed -> getMatchedTyped(typed, jacksonJsonConverter, typedBeansCollection))
                .ifPresent(corporation::setTyped);
    }
}
