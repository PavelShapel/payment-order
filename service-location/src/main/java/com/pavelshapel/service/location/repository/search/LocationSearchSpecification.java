package com.pavelshapel.service.location.repository.search;

import com.pavelshapel.service.location.model.Location;
import com.pavelshapel.jpa.spring.boot.starter.repository.search.SearchSpecification;
import org.springframework.stereotype.Component;

@Component
public class LocationSearchSpecification extends SearchSpecification<Location> {
}
