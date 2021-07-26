//package com.pavelshapel.common.module.service.jpa;
//
//import com.pavelshapel.common.module.entity.AbstractEntity;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//import java.util.List;
//
//public interface JpaService<T extends AbstractEntity> {
//    T create();
//
//    T createAndSave();
//
//    T save(T entity);
//
//    List<T> saveAll(Iterable<T> entities);
//
//
//    T findById(Long id);
//
//    List<T> findAllById(Iterable<Long> ids);
//
//    List<T> findAll();
//
//    Page<T> findAll(Pageable pageable);
//
//
//    void deleteById(Long id);
//
//    void deleteAll();
//
//
//    boolean existsById(Long id);
//
//    long getCount();
//}
