package com.pavelshapel.service.location.repository;

import com.pavelshapel.jpa.spring.boot.starter.repository.SpecificationDaoRepository;
import com.pavelshapel.service.location.model.Location;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDaoRepository extends SpecificationDaoRepository<Long, Location> {
}
