package com.pavelshapel.service.auth.repository;

import com.pavelshapel.jpa.spring.boot.starter.repository.AbstractJpaRepository;
import com.pavelshapel.service.auth.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AbstractJpaRepository<User> {
    User findByEmailEquals(String email);
}
