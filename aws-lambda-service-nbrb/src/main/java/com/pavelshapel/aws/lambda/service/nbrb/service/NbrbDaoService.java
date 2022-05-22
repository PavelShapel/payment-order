package com.pavelshapel.aws.lambda.service.nbrb.service;

import com.pavelshapel.aws.lambda.service.nbrb.model.Nbrb;
import com.pavelshapel.aws.lambda.service.nbrb.service.decorator.NbrbCacheableDecoratorDaoService;
import com.pavelshapel.core.spring.boot.starter.impl.service.AbstractDaoService;
import com.pavelshapel.core.spring.boot.starter.impl.service.decorator.DecorateDaoService;
import org.springframework.stereotype.Service;

@Service
@DecorateDaoService(decorations = {
        NbrbCacheableDecoratorDaoService.class
})
public class NbrbDaoService extends AbstractDaoService<String, Nbrb> {
    @Override
    public Nbrb create() {
        return new Nbrb();
    }
}
