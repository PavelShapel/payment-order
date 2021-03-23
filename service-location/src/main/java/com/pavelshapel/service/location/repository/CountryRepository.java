package com.pavelshapel.service.location.repository;

import com.pavelshapel.common.module.repository.AbstractJpaRepository;
import com.pavelshapel.service.location.entity.Country;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends AbstractJpaRepository<Country> {
    List<Country> findByNameContaining(String name);
}
