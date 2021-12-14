package com.pavelshapel.service.location.repository;

import com.pavelshapel.service.location.entity.LocationType;
import com.pavelshapel.jpa.spring.boot.starter.repository.AbstractJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationTypeJpaRepository extends AbstractJpaRepository<Long, LocationType> {
}
