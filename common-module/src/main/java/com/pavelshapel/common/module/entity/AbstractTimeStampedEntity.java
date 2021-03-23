package com.pavelshapel.common.module.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Setter(AccessLevel.NONE)
@MappedSuperclass
public abstract class AbstractTimeStampedEntity extends AbstractEntity {
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date inserted;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @PrePersist
    private void onInsert() {
        this.inserted = new Date();
        this.updated = this.inserted;
    }

    @PreUpdate
    private void onUpdate() {
        this.updated = new Date();
    }
}