package com.pavelshapel.service.location.repository.search;

import com.pavelshapel.service.location.model.LocationType;
import com.pavelshapel.jpa.spring.boot.starter.repository.search.SearchSpecification;
import org.springframework.stereotype.Component;

@Component
public class LocationTypeSearchSpecification extends SearchSpecification<LocationType> {
}
