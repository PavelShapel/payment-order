//package com.pavelshapel.common.module.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//
//@Getter(AccessLevel.PROTECTED)
//@Setter(AccessLevel.PROTECTED)
//@MappedSuperclass
//public abstract class AbstractEntity implements Serializable {
//    public static final String MANDATORY = "mandatory";
//
//    @Id
//    @Column(nullable = false, updatable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Version
//    @Setter(AccessLevel.NONE)
//    @Column(nullable = false)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @JsonIgnore
//    private Long version;
//}
