package com.pavelshapel.service.auth.repository;

import com.pavelshapel.jpa.spring.boot.starter.repository.AbstractJpaRepository;
import com.pavelshapel.service.auth.entity.Role;
import com.pavelshapel.service.auth.entity.RoleType;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends AbstractJpaRepository<Role> {
    Role findByRoleType(RoleType roleType);
}
