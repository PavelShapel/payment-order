package com.pavelshapel.service.location.repository;

import com.pavelshapel.service.location.entity.LocationType;
import com.pavelshapel.jpa.spring.boot.starter.repository.search.SearchCriteria;
import com.pavelshapel.jpa.spring.boot.starter.repository.search.SearchOperation;
import com.pavelshapel.service.location.MockLocationType;
import com.pavelshapel.service.location.provider.OneStringProvider;
import com.pavelshapel.service.location.provider.TwoStringProvider;
import com.pavelshapel.service.location.repository.search.LocationTypeSearchSpecification;
import com.pavelshapel.test.spring.boot.starter.layer.AbstractJpaRepositoryTest;
import com.pavelshapel.test.spring.boot.starter.layer.MockSearchCriteria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.context.annotation.Import;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Import(LocationTypeSearchSpecification.class)
class LocationTypeJpaRepositoryTest extends AbstractJpaRepositoryTest<Long, LocationType> implements MockLocationType, MockSearchCriteria {
    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void findAllByNameContaining_WithValidParam_ShouldSaveAndReturnListWithEntity(String name) {
        LocationType mockLocationType = getMockLocationType(name);
        LocationType savedLocationType = saveAndRetrieve(mockLocationType);
        SearchCriteria searchCriteriaName = getMockSearchCriteriaName(name.toLowerCase(), SearchOperation.CONTAINS);
        getSearchSpecification().setSearchCriteria(searchCriteriaName);

        List<LocationType> retrievedLocationType = getJpaRepository().findAll(getSearchSpecification());

        assertThat(retrievedLocationType)
                .isNotNull()
                .isNotEmpty()
                .contains(savedLocationType);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoStringProvider.class)
    void findAllByNameContaining_WithInvalidParam_ShouldSaveAndReturnEmptyList(String name, String searchName) {
        LocationType mockLocationType = getMockLocationType(name);
        saveAndRetrieve(mockLocationType);
        SearchCriteria searchCriteriaName = getMockSearchCriteriaName(searchName, SearchOperation.CONTAINS);
        getSearchSpecification().setSearchCriteria(searchCriteriaName);

        List<LocationType> retrievedLocationType = getJpaRepository().findAll(getSearchSpecification());

        assertThat(retrievedLocationType)
                .isNotNull()
                .isEmpty();
    }

    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void findAllByNameStartsWith_WithValidParam_ShouldSaveAndReturnListWithEntity(String name) {
        LocationType mockLocationType = getMockLocationType(name);
        LocationType savedLocationType = saveAndRetrieve(mockLocationType);
        SearchCriteria searchCriteriaName = getMockSearchCriteriaName(name.substring(0, 1), SearchOperation.STARTS_WITH);
        getSearchSpecification().setSearchCriteria(searchCriteriaName);

        List<LocationType> retrievedLocationType = getJpaRepository().findAll(getSearchSpecification());

        assertThat(retrievedLocationType)
                .isNotNull()
                .isNotEmpty()
                .contains(savedLocationType);
    }

    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void findAllByNameEqual_WithValidParam_ShouldSaveAndReturnListWithEntity(String name) {
        LocationType mockLocationType = getMockLocationType(name);
        LocationType savedLocationType = saveAndRetrieve(mockLocationType);
        SearchCriteria searchCriteriaName = getMockSearchCriteriaName(name, SearchOperation.EQUALS);
        getSearchSpecification().setSearchCriteria(searchCriteriaName);

        List<LocationType> retrievedLocationType = getJpaRepository().findAll(getSearchSpecification());

        assertThat(retrievedLocationType)
                .isNotNull()
                .isNotEmpty()
                .contains(savedLocationType);
    }

    @Test
    void saveAndRetrieve_WithNullParam_ShouldThrowException() {
        LocationType mockLocationType = getMockLocationType(null);
        assertThatThrownBy(() -> saveAndRetrieve(mockLocationType))
                .isInstanceOf(ConstraintViolationException.class);
    }
}