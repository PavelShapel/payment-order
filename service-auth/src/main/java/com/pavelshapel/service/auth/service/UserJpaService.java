package com.pavelshapel.service.auth.service;

import com.pavelshapel.jpa.spring.boot.starter.service.jpa.AbstractJpaService;
import com.pavelshapel.service.auth.entity.Role;
import com.pavelshapel.service.auth.entity.RoleType;
import com.pavelshapel.service.auth.entity.User;
import com.pavelshapel.service.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserJpaService extends AbstractJpaService<User> {
    @Autowired
    private RoleJpaService roleJpaService;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User create() {
        return new User();
    }

    @Override
    public User save(User entity) {
        Role roleUser = roleJpaService.findByRoleType(RoleType.ROLE_USER);
        entity.setPassword(entity.getPassword());
//        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setRoles(Collections.singleton(roleUser));
        return getJpaRepository().save(entity);
    }

    public User findByEmailEquals(String email){
        return ((UserRepository) getJpaRepository()).findByEmailEquals(email);
    }
}
