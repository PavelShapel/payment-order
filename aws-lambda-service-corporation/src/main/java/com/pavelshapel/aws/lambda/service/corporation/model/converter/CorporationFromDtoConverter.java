package com.pavelshapel.aws.lambda.service.corporation.model.converter;

import com.pavelshapel.aws.lambda.service.corporation.model.Corporation;
import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.common.module.dto.service.location.aws.CorporationDto;
import com.pavelshapel.core.spring.boot.starter.api.bean.BeansCollection;
import com.pavelshapel.core.spring.boot.starter.api.converter.DtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.converter.FromDtoConverter;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;
import com.pavelshapel.core.spring.boot.starter.api.service.DaoService;
import com.pavelshapel.core.spring.boot.starter.impl.model.TypedDto;
import com.pavelshapel.json.spring.boot.starter.converter.JsonConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@DtoConverter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class CorporationFromDtoConverter implements FromDtoConverter<String, CorporationDto, Corporation> {
    DaoService<String, Corporation> corporationDaoService;
    JsonConverter jacksonJsonConverter;
    BeansCollection<Typed<Type>> typedBeansCollection;

    @Override
    public Corporation convert(CorporationDto corporationDto) {
        Corporation corporation = new Corporation();
        corporation.setId(corporationDto.getId());
        setParent(corporationDto, corporation);
        corporation.setTyped(getTyped(corporationDto.getTyped()));
        return corporation;
    }

    private void setParent(CorporationDto corporationDto, Corporation corporation) {
        Optional.ofNullable(corporationDto.getParent())
                .map(corporationDaoService::findById)
                .ifPresent(corporation::setParent);
    }

    private Typed<Type> getTyped(Typed<String> typed) {
        return Optional.ofNullable(typed)
                .map(Typed::getType)
                .map(Type::valueOf)
                .flatMap(this::getTypedClass)
                .flatMap(targetClass -> jacksonJsonConverter.mapToPojo(((TypedDto) typed), targetClass))
                .orElse(null);
    }

    private Optional<Class<Typed<Type>>> getTypedClass(Type type) {
        return typedBeansCollection.getBean(typed -> typed.getType().equals(type))
                .map(Typed::getClass)
                .map(targetClass -> (Class<Typed<Type>>) targetClass);
    }
}
