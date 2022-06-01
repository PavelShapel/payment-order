package com.pavelshapel.service.location.repository;

import com.pavelshapel.jpa.spring.boot.starter.repository.SpecificationDaoRepository;
import com.pavelshapel.service.location.model.LocationType;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationTypeDaoRepository extends SpecificationDaoRepository<Long, LocationType> {
}
