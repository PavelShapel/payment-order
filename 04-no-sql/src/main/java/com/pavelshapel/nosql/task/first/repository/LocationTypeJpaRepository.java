package com.pavelshapel.nosql.task.first.repository;

import com.pavelshapel.jpa.spring.boot.starter.repository.AbstractJpaRepository;
import com.pavelshapel.nosql.task.first.entity.LocationType;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationTypeJpaRepository extends AbstractJpaRepository<LocationType> {
}
