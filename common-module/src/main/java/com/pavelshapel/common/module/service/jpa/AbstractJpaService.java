//package com.pavelshapel.common.module.service.jpa;
//
//import com.pavelshapel.common.module.entity.AbstractEntity;
//import com.pavelshapel.common.module.repository.AbstractJpaRepository;
//import lombok.Getter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Transactional
//public abstract class AbstractJpaService<T extends AbstractEntity> implements JpaService<T> {
//    @Getter
//    @Autowired
//    private AbstractJpaRepository<T> jpaRepository;
//
//    @Override
//    public T createAndSave() {
//        return jpaRepository.save(create());
//    }
//
//    @Override
//    public T save(T entity) {
//        return jpaRepository.save(entity);
//    }
//
//    @Override
//    public List<T> saveAll(Iterable<T> entities) {
//        return jpaRepository.saveAll(entities);
//    }
//
//
//    @Override
//    public T findById(Long id) {
//        return jpaRepository.findById(id).get();
//    }
//
//    @Override
//    public List<T> findAllById(Iterable<Long> ids) {
//        return jpaRepository.findAllById(ids);
//    }
//
//    @Override
//    public List<T> findAll() {
//        return jpaRepository.findAll();
//    }
//
//    @Override
//    public Page<T> findAll(Pageable pageable) {
//        return jpaRepository.findAll(pageable);
//    }
//
//
//    @Override
//    public void deleteById(Long id) {
//        jpaRepository.deleteById(id);
//    }
//
//    @Override
//    public void deleteAll() {
//        jpaRepository.deleteAll();
//    }
//
//
//    @Override
//    public boolean existsById(Long id) {
//        return jpaRepository.existsById(id);
//    }
//
//    @Override
//    public long getCount() {
//        return jpaRepository.count();
//    }
//}