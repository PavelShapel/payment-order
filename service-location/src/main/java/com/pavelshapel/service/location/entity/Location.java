package com.pavelshapel.service.location.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"parent_id", "name"}))
public class Location extends AbstractAuditableVersionEntity {
    @ManyToOne(targetEntity = Location.class)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Location parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private transient Set<Location> locations = new LinkedHashSet<>();

    @ManyToOne(targetEntity = LocationType.class)
    @JoinColumn(name = "location_type_id")
    @JsonIgnore
    private LocationType locationType;

    @Column(nullable = false)
    @NotBlank(message = MANDATORY)
    @Size(max = Byte.MAX_VALUE)
    private String name = MANDATORY;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long locationTypeId;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long parentId;
}

