//package com.pavelshapel.common.module.entity;
//
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.Column;
//import javax.persistence.EntityListeners;
//import javax.persistence.MappedSuperclass;
//import java.util.Date;
//
//
//@Getter(AccessLevel.PROTECTED)
//@Setter(AccessLevel.PROTECTED)
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
//public class Auditable<T> {
//    @CreatedBy
//    @Column
//    private T createdBy;
//
//    @CreatedDate
//    @Column
//    private Date createdDate;
//
//    @LastModifiedBy
//    @Column
//    private T lastModifiedBy;
//
//    @LastModifiedDate
//    @Column
//    private Date lastModifiedDate;
//}