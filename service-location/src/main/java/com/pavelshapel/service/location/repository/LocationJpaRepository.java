package com.pavelshapel.service.location.repository;

import com.pavelshapel.jpa.spring.boot.starter.repository.AbstractJpaRepository;
import com.pavelshapel.service.location.entity.Location;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationJpaRepository extends AbstractJpaRepository<Location> {
}
