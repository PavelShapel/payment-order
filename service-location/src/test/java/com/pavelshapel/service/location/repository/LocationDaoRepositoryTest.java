package com.pavelshapel.service.location.repository;

import com.pavelshapel.jpa.spring.boot.starter.service.search.SearchCriterion;
import com.pavelshapel.jpa.spring.boot.starter.service.search.SearchOperation;
import com.pavelshapel.service.location.MockLocation;
import com.pavelshapel.service.location.model.Location;
import com.pavelshapel.service.location.provider.OneStringProvider;
import com.pavelshapel.service.location.service.search.LocationSearchSpecification;
import com.pavelshapel.test.spring.boot.starter.layer.AbstractJpaDaoRepositoryTest;
import com.pavelshapel.test.spring.boot.starter.layer.MockSearchCriterion;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.context.annotation.Import;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

@Import(LocationSearchSpecification.class)
class LocationDaoRepositoryTest extends AbstractJpaDaoRepositoryTest<Long, Location> implements MockLocation, MockSearchCriterion {
    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void findAllByNameContaining_WithValidParam_ShouldReturnListWithEntity(String name) {
        Location mockLocation = getMockLocation(name);
        Location savedLocation = save(mockLocation);
        SearchCriterion searchCriterionName = getMockSearchCriterionName(name.toLowerCase(), SearchOperation.CONTAINS);
        getSearchSpecification().setSearchCriteria(singletonList(searchCriterionName));

        List<Location> result = getDaoRepository().findAll(getSearchSpecification());

        assertThat(result)
                .isNotEmpty()
                .contains(savedLocation);
    }

    
}