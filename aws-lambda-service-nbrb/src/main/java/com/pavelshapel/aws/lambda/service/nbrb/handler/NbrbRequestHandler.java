package com.pavelshapel.aws.lambda.service.nbrb.handler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.pavelshapel.aws.lambda.service.nbrb.model.Nbrb;
import com.pavelshapel.common.module.dto.aws.NbrbDto;
import com.pavelshapel.common.module.dto.aws.RatedDto;
import com.pavelshapel.core.spring.boot.starter.api.util.StreamUtils;
import com.pavelshapel.core.spring.boot.starter.enums.PrimitiveType;
import com.pavelshapel.jpa.spring.boot.starter.service.search.SearchCriterion;
import com.pavelshapel.json.spring.boot.starter.converter.JsonConverter;
import com.pavelshapel.web.spring.boot.starter.web.AbstractDaoRestController;
import com.pavelshapel.webflux.spring.boot.starter.api.ApiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.pavelshapel.core.spring.boot.starter.api.model.Rated.ABBREVIATION_FIELD;
import static com.pavelshapel.core.spring.boot.starter.api.model.Rated.DATE_FIELD;
import static com.pavelshapel.jpa.spring.boot.starter.service.search.SearchOperation.EQUALS;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;
import static org.apache.commons.lang3.math.NumberUtils.toScaledBigDecimal;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class NbrbRequestHandler implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    AbstractDaoRestController<String, Nbrb, NbrbDto> nbrbDaoRestController;
    ApiService<RatedDto, NbrbDto> nbrbApiService;
    StreamUtils streamUtils;
    JsonConverter jsonConverter;

    @Override
    public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent requestEvent) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(singletonMap(CONTENT_TYPE, APPLICATION_JSON_VALUE));
        String body;
        try {
            RatedDto ratedDto = jsonConverter.jsonToPojo(requestEvent.getBody(), RatedDto.class);
            SearchCriterion datedSearchCriterion = createDatedSearchCriterion(ratedDto);
            SearchCriterion abbreviatedSearchCriterion = createAbbreviatedSearchCriterion(ratedDto);
            List<SearchCriterion> searchCriteria = asList(datedSearchCriterion, abbreviatedSearchCriterion);
            NbrbDto nbrbDto = getNbrbDto(ratedDto, searchCriteria);
            body = calculateAmount(ratedDto, nbrbDto).toString();
            return response
                    .withStatusCode(OK.value())
                    .withBody(body);
        } catch (Exception exception) {
            body = jsonConverter.pojoToJson(singletonMap("exceptionMessage", exception.getMessage()));
            return response
                    .withStatusCode(INTERNAL_SERVER_ERROR.value())
                    .withBody(body);
        }
    }

    private SearchCriterion createDatedSearchCriterion(RatedDto ratedDto) {
        SearchCriterion searchCriterion = new SearchCriterion();
        searchCriterion.setField(DATE_FIELD);
        searchCriterion.setOperation(EQUALS);
        String value = ratedDto.getDate().atStartOfDay().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String valueType = PrimitiveType.LOCAL_DATE.name();
        searchCriterion.setValue(value + ";" + valueType);
        return searchCriterion;
    }

    private SearchCriterion createAbbreviatedSearchCriterion(RatedDto ratedDto) {
        SearchCriterion searchCriterion = new SearchCriterion();
        searchCriterion.setField(ABBREVIATION_FIELD);
        searchCriterion.setOperation(EQUALS);
        String value = ratedDto.getAbbreviation();
        String valueType = PrimitiveType.STRING.name();
        searchCriterion.setValue(value + ";" + valueType);
        return searchCriterion;
    }

    private NbrbDto getNbrbDto(RatedDto ratedDto, List<SearchCriterion> searchCriteria) {
        return Optional.ofNullable(nbrbDaoRestController.findAll(searchCriteria))
                .map(HttpEntity::getBody)
                .orElseGet(Collections::emptyList)
                .stream()
                .collect(streamUtils.toSingleton())
                .orElseGet(() -> getAndSaveNbrbDto(ratedDto));
    }

    private NbrbDto getAndSaveNbrbDto(RatedDto ratedDto) {
        return nbrbApiService.get(ratedDto)
                .map(nbrbDaoRestController::save)
                .map(HttpEntity::getBody)
                .orElseThrow(() -> new IllegalArgumentException(ratedDto.toString()));
    }

    private BigDecimal calculateAmount(RatedDto ratedDto, NbrbDto nbrbDto) {
        return toScaledBigDecimal(
                ratedDto.getAmount() / nbrbDto.getScale() * nbrbDto.getRate(),
                ratedDto.getPrecision(),
                RoundingMode.HALF_EVEN);
    }
}
