package com.pavelshapel.aws.lambda.service.nbrb.handler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.pavelshapel.aws.lambda.service.nbrb.model.Nbrb;
import com.pavelshapel.aws.lambda.service.nbrb.service.api.ApiService;
import com.pavelshapel.common.module.dto.aws.NbrbDto;
import com.pavelshapel.core.spring.boot.starter.impl.model.RatedDto;
import com.pavelshapel.core.spring.boot.starter.impl.web.search.SearchCriteria;
import com.pavelshapel.web.spring.boot.starter.web.AbstractDaoRestController;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import static com.pavelshapel.core.spring.boot.starter.api.model.Rated.DATE_FIELD;
import static com.pavelshapel.core.spring.boot.starter.impl.web.search.SearchOperation.EQUALS;
import static java.math.RoundingMode.HALF_UP;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.util.Collections.singletonMap;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class NbrbRequestHandler implements Function<RatedDto, APIGatewayProxyResponseEvent> {
    AbstractDaoRestController<String, Nbrb, NbrbDto> nbrbDaoRestController;
    ApiService<RatedDto, NbrbDto> nbrbApiService;

    @Override
    public APIGatewayProxyResponseEvent apply(RatedDto ratedDto) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(singletonMap(CONTENT_TYPE, APPLICATION_JSON_VALUE));
        String body;
        try {
            SearchCriteria datedSearchCriteria = createDatedSearchCriteria(ratedDto);
            NbrbDto nbrbDto = getNbrbDto(ratedDto, datedSearchCriteria);
            body = calculateAmount(ratedDto, nbrbDto).toString();
            return response
                    .withStatusCode(HTTP_OK)
                    .withBody(body);
        } catch (Exception exception) {
            body = String.format("{exceptionMessage: %s}", exception.getMessage());
            return response
                    .withStatusCode(HTTP_INTERNAL_ERROR)
                    .withBody(body);
        }
    }

    private SearchCriteria createDatedSearchCriteria(RatedDto ratedDto) {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setField(DATE_FIELD);
        searchCriteria.setOperation(EQUALS);
        searchCriteria.setValue(ratedDto.getDate().atStartOfDay().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return searchCriteria;
    }

    private NbrbDto getNbrbDto(RatedDto ratedDto, SearchCriteria searchCriteria) {
        return Optional.ofNullable(nbrbDaoRestController.findAll(searchCriteria))
                .map(HttpEntity::getBody)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(nbrbDto -> Objects.equals(ratedDto.getAbbreviation(), nbrbDto.getAbbreviation()))
                .findFirst()
                .orElseGet(() -> getAndSaveNbrbDto(ratedDto));
    }

    private NbrbDto getAndSaveNbrbDto(RatedDto ratedDto) {
        return Optional.of(nbrbApiService.get(ratedDto))
                .map(nbrbDaoRestController::save)
                .map(HttpEntity::getBody)
                .orElseThrow(() -> new IllegalArgumentException(ratedDto.toString()));
    }

    private Double calculateAmount(RatedDto ratedDto, NbrbDto nbrbDto) {
        return round(ratedDto.getAmount() / nbrbDto.getScale() * nbrbDto.getRate(), ratedDto.getPrecision());
    }

    public Double round(Double value, Integer scale) {
        return Optional.ofNullable(value)
                .map(BigDecimal::valueOf)
                .map(bigDecimal -> bigDecimal.setScale(scale, HALF_UP))
                .map(BigDecimal::doubleValue)
                .orElseThrow(IllegalArgumentException::new);
    }
}
