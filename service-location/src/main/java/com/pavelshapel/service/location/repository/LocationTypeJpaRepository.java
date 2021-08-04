package com.pavelshapel.service.location.repository;

import com.pavelshapel.jpa.spring.boot.starter.repository.AbstractJpaRepository;
import com.pavelshapel.service.location.entity.LocationType;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationTypeJpaRepository extends AbstractJpaRepository<LocationType> {
}
