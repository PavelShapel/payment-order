package com.pavelshapel.service.location.repository;

import com.pavelshapel.jpa.spring.boot.starter.repository.DaoRepository;
import com.pavelshapel.service.location.model.LocationType;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationTypeDaoRepository extends DaoRepository<Long, LocationType> {
}
