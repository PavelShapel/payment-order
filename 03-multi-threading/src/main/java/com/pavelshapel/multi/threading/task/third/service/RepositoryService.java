package com.pavelshapel.multi.threading.task.third.service;

import com.pavelshapel.multi.threading.task.third.entity.Entity;

import java.util.List;

public interface RepositoryService<T extends Entity> {
    T get(Long id);

    List<T> getAll();
}
