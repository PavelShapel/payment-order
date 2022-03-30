package com.pavelshapel.service.location.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pavelshapel.jpa.spring.boot.starter.model.AbstractAuditableVersionEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class LocationType extends AbstractAuditableVersionEntity<Long> {
    @OneToMany(mappedBy = "locationType", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private transient Set<Location> locations = new LinkedHashSet<>();

    @Column(nullable = false, unique = true)
    @NotBlank(message = MANDATORY)
    @Size(max = Byte.MAX_VALUE)
    private String name = MANDATORY;
}

