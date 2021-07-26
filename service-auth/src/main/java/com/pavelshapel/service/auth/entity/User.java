package com.pavelshapel.service.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pavelshapel.jpa.spring.boot.starter.entity.AbstractAuditableVersionEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class User extends AbstractAuditableVersionEntity {
    @Column(unique = true, nullable = false)
    @Size(max = Byte.MAX_VALUE)
    @NotBlank(message = MANDATORY)
    private String email = MANDATORY;

    @Column(nullable = false)
    @Size(max = Byte.MAX_VALUE)
    @NotBlank(message = MANDATORY)
    private String password = MANDATORY;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private transient Set<Role> roles = new LinkedHashSet<>();
}
