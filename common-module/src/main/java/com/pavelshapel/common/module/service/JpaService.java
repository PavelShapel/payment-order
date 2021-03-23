package com.pavelshapel.common.module.service;

import com.pavelshapel.common.module.entity.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JpaService<T extends AbstractEntity> {
    T create();

    T createAndSave();

    T getOne(Long id);

    T findById(Long id);

    List<T> findAllById(Iterable<Long> ids);

    List<T> findAll();

    Page<T> findAll(Pageable pageable);

    T save(T entity);

    List<T> saveAll(Iterable<T> entities);

    Long deleteById(T entity);

    Long deleteById(Long id);

    void deleteAll();

    void deleteAll(Iterable<? extends T> entities);

    void existsById(Long id);

    long getCount();
}
