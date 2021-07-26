package com.pavelshapel.service.auth.service;

import com.pavelshapel.jpa.spring.boot.starter.service.jpa.AbstractJpaService;
import com.pavelshapel.service.auth.entity.Role;
import com.pavelshapel.service.auth.entity.RoleType;
import com.pavelshapel.service.auth.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleJpaService extends AbstractJpaService<Role> {
    @Override
    public Role create() {
        return new Role();
    }

    public Role findByRoleType(RoleType roleType) {
        return ((RoleRepository) getJpaRepository()).findByRoleType(roleType);
    }
}
