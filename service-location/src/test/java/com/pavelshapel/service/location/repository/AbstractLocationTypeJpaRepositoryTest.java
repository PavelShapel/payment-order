package com.pavelshapel.service.location.repository;

import com.pavelshapel.jpa.spring.boot.starter.repository.search.SearchCriteria;
import com.pavelshapel.jpa.spring.boot.starter.repository.search.SearchOperation;
import com.pavelshapel.service.location.entity.LocationType;
import com.pavelshapel.test.spring.boot.starter.layer.AbstractJpaRepositoryTest;


public abstract class AbstractLocationTypeJpaRepositoryTest extends AbstractJpaRepositoryTest<LocationType> {
    protected LocationType getLocationType(String name) {
        LocationType locationType = new LocationType();
        locationType.setName(name);
        return locationType;
    }

    protected SearchCriteria getSearchCriteriaName(String value, SearchOperation searchOperation) {
        return getSearchCriteria(value, "name", searchOperation);
    }
}
