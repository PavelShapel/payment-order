package com.pavelshapel.service.location.service;

import com.pavelshapel.common.module.service.AbstractJpaService;
import com.pavelshapel.service.location.entity.Country;
import org.springframework.stereotype.Service;

@Service
public class CountryJpaService extends AbstractJpaService<Country> {

    @Override
    public Country create() {
        return new Country();
    }
}