package com.pavelshapel.service.location.repository;

import com.pavelshapel.jpa.spring.boot.starter.service.search.SearchCriterion;
import com.pavelshapel.jpa.spring.boot.starter.service.search.SearchOperation;
import com.pavelshapel.service.location.MockLocationType;
import com.pavelshapel.service.location.model.LocationType;
import com.pavelshapel.service.location.provider.OneStringProvider;
import com.pavelshapel.service.location.provider.TwoStringProvider;
import com.pavelshapel.service.location.service.search.LocationTypeSearchSpecification;
import com.pavelshapel.test.spring.boot.starter.layer.AbstractJpaDaoRepositoryTest;
import com.pavelshapel.test.spring.boot.starter.layer.MockSearchCriterion;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.context.annotation.Import;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Import(LocationTypeSearchSpecification.class)
class LocationTypeDaoRepositoryTest extends AbstractJpaDaoRepositoryTest<Long, LocationType> implements MockLocationType, MockSearchCriterion {
    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void findAllByNameContaining_WithValidParam_ShouldReturnListWithEntity(String name) {
        LocationType mockLocationType = getMockLocationType(name);
        LocationType savedLocationType = save(mockLocationType);
        SearchCriterion searchCriterionName = getMockSearchCriterionName(name.toLowerCase(), SearchOperation.CONTAINS);
        getSearchSpecification().setSearchCriteria(singletonList(searchCriterionName));

        List<LocationType> result = getDaoRepository().findAll(getSearchSpecification());

        assertThat(result)
                .isNotEmpty()
                .contains(savedLocationType);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoStringProvider.class)
    void findAllByNameContaining_WithInvalidParam_ShouldReturnEmptyList(String name, String searchName) {
        LocationType mockLocationType = getMockLocationType(name);
        save(mockLocationType);
        SearchCriterion searchCriterionName = getMockSearchCriterionName(searchName, SearchOperation.CONTAINS);
        getSearchSpecification().setSearchCriteria(singletonList(searchCriterionName));

        List<LocationType> result = getDaoRepository().findAll(getSearchSpecification());

        assertThat(result)
                .isNotNull()
                .isEmpty();
    }

    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void findAllByNameStartsWith_WithValidParam_ShouldReturnListWithEntity(String name) {
        LocationType mockLocationType = getMockLocationType(name);
        LocationType savedLocationType = save(mockLocationType);
        SearchCriterion searchCriterionName = getMockSearchCriterionName(name.substring(0, 1), SearchOperation.STARTS_WITH);
        getSearchSpecification().setSearchCriteria(singletonList(searchCriterionName));

        List<LocationType> result = getDaoRepository().findAll(getSearchSpecification());

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .contains(savedLocationType);
    }

    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void findAllByNameEqual_WithValidParam_ShouldReturnListWithEntity(String name) {
        LocationType mockLocationType = getMockLocationType(name);
        LocationType savedLocationType = save(mockLocationType);
        SearchCriterion searchCriterionName = getMockSearchCriterionName(name, SearchOperation.EQUALS);
        getSearchSpecification().setSearchCriteria(singletonList(searchCriterionName));

        List<LocationType> result = getDaoRepository().findAll(getSearchSpecification());

        assertThat(result)
                .isNotEmpty()
                .contains(savedLocationType);
    }

    @ParameterizedTest
    @NullSource
    void save_WithNullParam_ShouldThrowException(String name) {
        LocationType mockLocationType = getMockLocationType(name);
        assertThatThrownBy(() -> save(mockLocationType))
                .isInstanceOf(ConstraintViolationException.class);
    }
}