package com.pavelshapel.service.location.repository;

import com.pavelshapel.service.location.entity.Location;
import com.pavelshapel.jpa.spring.boot.starter.repository.AbstractJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationJpaRepository extends AbstractJpaRepository<Long, Location> {
}
