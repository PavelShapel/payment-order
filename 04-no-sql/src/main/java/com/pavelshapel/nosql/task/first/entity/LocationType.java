package com.pavelshapel.nosql.task.first.entity;

import com.pavelshapel.jpa.spring.boot.starter.entity.AbstractAuditableVersionEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class LocationType extends AbstractAuditableVersionEntity {
    @Column(nullable = false, unique = true)
    @NotBlank(message = MANDATORY)
    @Size(max = Byte.MAX_VALUE)
    private String name = MANDATORY;
}

