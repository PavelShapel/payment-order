package com.pavelshapel.multi.threading.task.third.service;

import com.pavelshapel.multi.threading.task.third.service.impl.EntityGenerator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public abstract class AbstractService {
    @Autowired
    private EntityGenerator entityGenerator;
}
