package com.pavelshapel.common.module.service;

import com.pavelshapel.common.module.entity.AbstractEntity;
import com.pavelshapel.common.module.repository.AbstractJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Transactional
public abstract class AbstractJpaService<T extends AbstractEntity> implements JpaService<T> {
    @Autowired
    private AbstractJpaRepository<T> jpaRepository;

    @Override
    public T createAndSave() {
        return jpaRepository.save(create());
    }

    @Override
    public T getOne(Long id) {
        return jpaRepository.getOne(id);
    }

    @Override
    public T findById(Long id) {
        return jpaRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("[id: %d]", id)));
    }

    @Override
    public List<T> findAllById(Iterable<Long> ids) {
        return jpaRepository.findAllById(ids);
    }

    @Override
    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable);
    }

    @Override
    public T save(T entity) {
        return jpaRepository.save(entity);
    }

    @Override
    public List<T> saveAll(Iterable<T> entities) {
        return jpaRepository.saveAll(entities);
    }


    @Override
    public Long deleteById(T entity) {
        final Long id = entity.getId();
        jpaRepository.delete(entity);
        return id;
    }

    @Override
    public Long deleteById(Long id) {
        jpaRepository.deleteById(id);
        return id;
    }

    @Override
    public void deleteAll() {
        jpaRepository.deleteAll();
    }


    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        jpaRepository.deleteAll(entities);
    }

    @Override
    public void existsById(Long id) {
        jpaRepository.existsById(id);
    }

    @Override
    public long getCount() {
        return jpaRepository.count();
    }
}