package com.pavelshapel.service.location.repository;

import com.pavelshapel.jpa.spring.boot.starter.repository.AbstractJpaRepository;
import com.pavelshapel.service.location.entity.Location;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationJpaRepository extends AbstractJpaRepository<Location> {
    List<Location> findByNameIgnoreCaseContaining(String name);
}
