package com.pavelshapel.common.module.service.jpa.decorator;

import com.pavelshapel.common.module.entity.AbstractEntity;
import com.pavelshapel.common.module.service.jpa.JpaService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Getter
@Setter
public abstract class AbstractDecoratorJpaService<T extends AbstractEntity> implements JpaService<T> {
    private JpaService<T> wrapped;

    @Override
    public T create() {
        return wrapped.create();
    }

    @Override
    public T createAndSave() {
        return wrapped.createAndSave();
    }

    @Override
    public T save(T entity) {
        return wrapped.save(entity);
    }

    @Override
    public List<T> saveAll(Iterable<T> entities) {
        return wrapped.saveAll(entities);
    }


    @Override
    public T findById(Long id) {
        return wrapped.findById(id);
    }

    @Override
    public List<T> findAllById(Iterable<Long> ids) {
        return wrapped.findAllById(ids);
    }

    @Override
    public List<T> findAll() {
        return wrapped.findAll();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return wrapped.findAll(pageable);
    }


    @Override
    public void deleteById(Long id) {
        wrapped.deleteById(id);
    }

    @Override
    public void deleteAll() {
        wrapped.deleteAll();
    }


    @Override
    public boolean existsById(Long id) {
        return wrapped.existsById(id);
    }

    @Override
    public long getCount() {
        return wrapped.getCount();
    }
}
