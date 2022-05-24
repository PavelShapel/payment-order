package com.pavelshapel.aws.lambda.service.nbrb.service;

import com.pavelshapel.aws.lambda.service.nbrb.AbstractTest;
import com.pavelshapel.common.module.dto.aws.NbrbDto;
import com.pavelshapel.common.module.dto.aws.RatedDto;
import com.pavelshapel.webflux.spring.boot.starter.WebFluxStarterAutoConfiguration;
import com.pavelshapel.webflux.spring.boot.starter.api.ApiService;
import com.pavelshapel.webflux.spring.boot.starter.properties.WebFluxProperties;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static com.pavelshapel.core.spring.boot.starter.api.model.Entity.ID_FIELD;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {
        WebFluxStarterAutoConfiguration.class,
        NbrbApiService.class
})
@EnableConfigurationProperties(value = WebFluxProperties.class)
@Import(AnnotationAwareAspectJAutoProxyCreator.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class NbrbApiServiceTest extends AbstractTest {
    @Autowired
    ApiService<RatedDto, NbrbDto> nbrbApiService;

    @Test
    void get_WithValidParams_ShouldReturnValidResponse() {
        RatedDto ratedDto = new RatedDto();
        ratedDto.setAbbreviation(USD);

        NbrbDto nbrbDto = nbrbApiService.get(ratedDto);

        assertThat(nbrbDto)
                .isNotNull()
                .hasNoNullFieldsOrPropertiesExcept(ID_FIELD);
    }
}