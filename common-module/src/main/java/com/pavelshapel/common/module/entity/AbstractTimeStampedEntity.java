//package com.pavelshapel.common.module.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Data
//@ToString(callSuper = true)
//@EqualsAndHashCode(callSuper = true)
//@MappedSuperclass
//public abstract class AbstractTimeStampedEntity extends AbstractEntity {
//    @Column(nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    @Setter(AccessLevel.NONE)
//    @JsonIgnore
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Date inserted;
//
//    @Column(nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    @Setter(AccessLevel.NONE)
//    @JsonIgnore
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Date updated;
//
//    @PrePersist
//    private void onInsert() {
//        this.inserted = new Date();
//        this.updated = this.inserted;
//    }
//
//    @PreUpdate
//    private void onUpdate() {
//        this.updated = new Date();
//    }
//}