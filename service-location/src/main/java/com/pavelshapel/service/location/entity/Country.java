package com.pavelshapel.service.location.entity;

import com.pavelshapel.common.module.entity.AbstractTimeStampedEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class Country extends AbstractTimeStampedEntity {
    @Column(nullable = false)
    @NotBlank(message = MANDATORY)
    @Size(max = Byte.MAX_VALUE)
    private String name = MANDATORY;
}

