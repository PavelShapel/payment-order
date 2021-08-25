package com.pavelshapel.service.location.repository;

import com.pavelshapel.jpa.spring.boot.starter.repository.search.SearchCriteria;
import com.pavelshapel.jpa.spring.boot.starter.repository.search.SearchOperation;
import com.pavelshapel.service.location.ServiceLocationApplication;
import com.pavelshapel.service.location.entity.LocationType;
import com.pavelshapel.service.location.provider.OneStringProvider;
import com.pavelshapel.service.location.provider.TwoStringProvider;
import com.pavelshapel.service.location.repository.search.LocationTypeSearchSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.test.context.ContextConfiguration;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ContextConfiguration(classes = {
        ServiceLocationApplication.class,
        LocationTypeSearchSpecification.class
})
class LocationTypeJpaRepositoryTest extends AbstractLocationTypeJpaRepositoryTest {
    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void findAllByNameContaining_WithValidParam_ShouldSaveAndReturnListWithEntity(String name) {
        LocationType locationType = getLocationType(name);
        LocationType savedLocationType = saveAndRetrieve(locationType);
        SearchCriteria searchCriteriaName = getSearchCriteriaName(name.toLowerCase(), SearchOperation.CONTAINS);
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
        LocationType locationType = getLocationType(name);
        saveAndRetrieve(locationType);
        SearchCriteria searchCriteriaName = getSearchCriteriaName(searchName, SearchOperation.CONTAINS);
        getSearchSpecification().setSearchCriteria(searchCriteriaName);

        List<LocationType> retrievedLocationType = getJpaRepository().findAll(getSearchSpecification());

        assertThat(retrievedLocationType)
                .isNotNull()
                .isEmpty();
    }

    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void findAllByNameStartsWith_WithValidParam_ShouldSaveAndReturnListWithEntity(String name) {
        LocationType locationType = getLocationType(name);
        LocationType savedLocationType = saveAndRetrieve(locationType);
        SearchCriteria searchCriteriaName = getSearchCriteriaName(name.substring(0,1), SearchOperation.STARTS_WITH);
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
        LocationType locationType = getLocationType(name);
        LocationType savedLocationType = saveAndRetrieve(locationType);
        SearchCriteria searchCriteriaName = getSearchCriteriaName(name, SearchOperation.EQUAL);
        getSearchSpecification().setSearchCriteria(searchCriteriaName);

        List<LocationType> retrievedLocationType = getJpaRepository().findAll(getSearchSpecification());

        assertThat(retrievedLocationType)
                .isNotNull()
                .isNotEmpty()
                .contains(savedLocationType);
    }

    @Test
    void saveAndRetrieve_WithNullParam_ShouldThrowException() {
        LocationType locationType = getLocationType(null);
        assertThatThrownBy(() -> saveAndRetrieve(locationType))
                .isInstanceOf(ConstraintViolationException.class);
    }
}