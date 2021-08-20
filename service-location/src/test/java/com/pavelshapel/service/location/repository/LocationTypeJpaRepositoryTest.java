package com.pavelshapel.service.location.repository;

import com.pavelshapel.jpa.spring.boot.starter.JpaAuditingConfiguration;
import com.pavelshapel.jpa.spring.boot.starter.repository.search.SearchCriteria;
import com.pavelshapel.jpa.spring.boot.starter.repository.search.SearchOperation;
import com.pavelshapel.service.location.AbstractTest;
import com.pavelshapel.service.location.ServiceLocationApplication;
import com.pavelshapel.service.location.entity.LocationType;
import com.pavelshapel.service.location.provider.OneStringProvider;
import com.pavelshapel.service.location.provider.TwoStringProvider;
import com.pavelshapel.service.location.repository.search.LocationTypeSearchSpecification;
import com.pavelshapel.test.spring.boot.starter.container.postgres.PostgreSQLExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@ContextConfiguration(classes = {
        ServiceLocationApplication.class,
        JpaAuditingConfiguration.class
})
@AutoConfigureTestDatabase(replace = NONE)
@ExtendWith(PostgreSQLExtension.class)
class LocationTypeJpaRepositoryTest extends AbstractTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private LocationTypeJpaRepository locationTypeJpaRepository;

    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void findAllByNameContaining_WithValidParam_ShouldSaveAndReturnListWithEntity(String name) {
        LocationType locationType = getLocationType(name);
        LocationType savedLocationType = testEntityManager.persistFlushFind(locationType);
        SearchCriteria searchCriteriaName = getSearchCriteriaName(name, SearchOperation.CONTAINS);
        LocationTypeSearchSpecification locationTypeSearchSpecification = new LocationTypeSearchSpecification();
        locationTypeSearchSpecification.setSearchCriteria(searchCriteriaName);

        List<LocationType> retrievedLocationType = locationTypeJpaRepository.findAll(locationTypeSearchSpecification);

        assertThat(retrievedLocationType)
                .isNotNull()
                .isNotEmpty()
                .contains(savedLocationType);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoStringProvider.class)
    void findAllByNameContaining_WithInvalidParam_ShouldSaveAndReturnEmptyList(String name, String searchName) {
        LocationType locationType = getLocationType(name);
        testEntityManager.persistFlushFind(locationType);
        SearchCriteria searchCriteriaName = getSearchCriteriaName(searchName, SearchOperation.CONTAINS);
        LocationTypeSearchSpecification locationTypeSearchSpecification = new LocationTypeSearchSpecification();
        locationTypeSearchSpecification.setSearchCriteria(searchCriteriaName);

        List<LocationType> retrievedLocationType = locationTypeJpaRepository.findAll(locationTypeSearchSpecification);

        assertThat(retrievedLocationType)
                .isNotNull()
                .isEmpty();
    }

    @Test
    void persistFlushFind_WithNullParam_ShouldThrowException() {
        LocationType locationType = getLocationType(null);
        assertThatThrownBy(() -> testEntityManager.persistFlushFind(locationType))
                .isInstanceOf(ConstraintViolationException.class);
    }
}