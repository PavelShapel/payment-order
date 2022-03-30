package com.pavelshapel.service.location.repository;

import com.pavelshapel.core.spring.boot.starter.api.repository.SpecificationDaoRepository;
import com.pavelshapel.service.location.model.LocationType;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationTypeDaoRepository extends SpecificationDaoRepository<Long, LocationType> {
}
