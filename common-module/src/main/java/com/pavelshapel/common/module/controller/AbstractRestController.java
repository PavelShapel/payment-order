package com.pavelshapel.common.module.controller;

import com.pavelshapel.aop.spring.boot.starter.log.method.result.LogMethodResult;
import com.pavelshapel.common.module.entity.AbstractEntity;
import com.pavelshapel.common.module.service.jpa.JpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public abstract class AbstractRestController<T extends AbstractEntity> {
    public static final String ID = "/{id:[\\d]+}";

    @Autowired
    private JpaService<T> jpaService;

    @LogMethodResult
    @PostMapping
    public ResponseEntity<T> post(@RequestBody @NonNull @Valid T entity) {
        T responseEntity = jpaService.save(entity);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(ID)
                .buildAndExpand(responseEntity.getId()).toUri();

        return ResponseEntity.created(location).body(responseEntity);
    }

    @LogMethodResult
    @PutMapping(ID)
    public ResponseEntity<T> put(@PathVariable @NonNull Long id, @RequestBody @NonNull @Valid T entity) {
        if (jpaService.existsById(id)) {
            entity.setId(id);
        }

        return ResponseEntity.ok(entity);
    }


    @LogMethodResult
    @GetMapping("/{id}")
    public ResponseEntity<T> get(@PathVariable @NonNull Long id) {
        return ResponseEntity.ok(jpaService.findById(id));
    }

    @LogMethodResult
    @GetMapping
    public ResponseEntity<List<T>> get() {
        return ResponseEntity.ok(jpaService.findAll());
    }

    @LogMethodResult
    @DeleteMapping(ID)
    public ResponseEntity<Void> delete(@PathVariable @NonNull Long id) {
        jpaService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}