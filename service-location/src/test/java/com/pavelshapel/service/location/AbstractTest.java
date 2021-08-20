package com.pavelshapel.service.location;

import com.pavelshapel.jpa.spring.boot.starter.repository.search.SearchCriteria;
import com.pavelshapel.jpa.spring.boot.starter.repository.search.SearchOperation;
import com.pavelshapel.service.location.entity.Location;
import com.pavelshapel.service.location.entity.LocationType;

public abstract class AbstractTest {
    protected LocationType getLocationType(String name) {
        LocationType locationType = new LocationType();
        locationType.setName(name);
        return locationType;
    }

    protected Location getLocation(String name) {
        Location location = new Location();
        location.setName(name);
        return location;
    }

    protected SearchCriteria getSearchCriteriaName(String name, SearchOperation searchOperation) {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setField("name");
        searchCriteria.setValue(name);
        searchCriteria.setOperation(searchOperation);
        return searchCriteria;
    }
}
