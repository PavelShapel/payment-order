//package com.pavelshapel.common.module.service.jpa.decorator;
//
//import com.pavelshapel.common.module.entity.AbstractEntity;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.StreamSupport;
//
//public abstract class ThrowableDecoratorJpaService<T extends AbstractEntity>
//        extends AbstractDecoratorJpaService<T> {
//
//    @Override
//    public T findById(Long id) {
//        verifyId(id);
//
//        return super.findById(id);
//    }
//
//    @Override
//    public List<T> findAllById(Iterable<Long> ids) {
//        final List<T> foundEntities = super.findAllById(ids);
//
//        verifyAllId(foundEntities, ids);
//
//        return foundEntities;
//    }
//
//    @Override
//    public List<T> findAll() {
//        final List<T> foundEntities = super.findAll();
//
//        verifyAllId(foundEntities);
//
//        return foundEntities;
//    }
//
//    @Override
//    public Page<T> findAll(Pageable pageable) {
//        final Page<T> foundEntities = super.findAll(pageable);
//
//        verifyCount(foundEntities.getTotalElements());
//
//        return foundEntities;
//    }
//
//
//    @Override
//    public void deleteById(Long id) {
//        verifyId(id);
//
//        super.deleteById(id);
//    }
//
//    @Override
//    public void deleteAll() {
//        verifyCount(super.getCount());
//
//        super.deleteAll();
//    }
//
//    @Override
//    public boolean existsById(Long id) {
//        verifyId(id);
//
//        return super.existsById(id);
//    }
//
//
//    private void verifyId(Long id) {
//        if (!super.existsById(id)) {
//            throw createEntityNotFoundException(Collections.singletonList(id));
//        }
//    }
//
//    private void verifyAllId(List<T> entities, Iterable<Long> ids) {
//        if (entities.isEmpty()) {
//            throw createEntityNotFoundException(ids);
//        }
//    }
//
//    private void verifyAllId(List<T> entities) {
//        verifyAllId(entities, null);
//    }
//
//    private void verifyCount(Long count) {
//        if (count == 0) {
//            throw createEntityNotFoundException(Collections.singletonList(null));
//        }
//    }
//
//    private RuntimeException createEntityNotFoundException(Iterable<Long> ids) {
//        final String stringOfIds = StreamSupport.stream(ids.spliterator(), false)
//                .map(String::valueOf)
//                .collect(Collectors.joining(", "));
//
//        return new EntityNotFoundException(
//                String.format(
//                        "service: [%s]; ids: [%s]",
//                        getClass().getSimpleName(),
//                        stringOfIds
//                )
//        );
//    }
//}
