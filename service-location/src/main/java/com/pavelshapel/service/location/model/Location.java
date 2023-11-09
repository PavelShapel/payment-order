package com.pavelshapel.service.location.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pavelshapel.core.spring.boot.starter.api.model.ParentalEntity;
import com.pavelshapel.jpa.spring.boot.starter.model.AbstractCreatedVersionedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"parent_id", "name"}))
public class Location extends AbstractCreatedVersionedEntity<Long> implements ParentalEntity<Long, Location> {
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
}

