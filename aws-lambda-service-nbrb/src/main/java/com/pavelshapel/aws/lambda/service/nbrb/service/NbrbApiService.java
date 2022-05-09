package com.pavelshapel.aws.lambda.service.nbrb.service;

import com.pavelshapel.aws.lambda.service.nbrb.service.api.ApiService;
import com.pavelshapel.common.module.dto.aws.NbrbDto;
import com.pavelshapel.core.spring.boot.starter.impl.model.RatedDto;
import com.pavelshapel.web.spring.boot.starter.properties.WebProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import static java.time.Duration.ofMillis;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NbrbApiService implements ApiService<RatedDto, NbrbDto> {
    WebClient webClient;
    WebProperties webProperties;

    @Override
    public NbrbDto get(RatedDto ratedDto) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/exrates/rates/{abbreviation}")
                        .queryParam("parammode", "2")
                        .queryParam("ondate", ratedDto.getDate().format(ISO_LOCAL_DATE))
                        .build(ratedDto.getAbbreviation()))
                .retrieve()
                .bodyToMono(NbrbDto.class)
                .retryWhen(Retry.fixedDelay(3, ofMillis(webProperties.getWebClient().getTimeout())))
                .block();
    }
}