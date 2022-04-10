package com.pavelshapel.aws.lambda.service.corporation.service;

import com.pavelshapel.aws.lambda.service.corporation.model.Corporation;
import com.pavelshapel.aws.lambda.service.corporation.service.decorator.CorporationCacheableDecoratorDaoService;
import com.pavelshapel.aws.lambda.service.corporation.service.decorator.CorporationCrudDecoratorDaoService;
import com.pavelshapel.aws.lambda.service.corporation.service.decorator.CorporationThrowableDecoratorDaoService;
import com.pavelshapel.core.spring.boot.starter.impl.service.AbstractDaoService;
import com.pavelshapel.core.spring.boot.starter.impl.service.decorator.DecorateDaoService;
import org.springframework.stereotype.Service;

@Service
@DecorateDaoService(decorations = {
        CorporationCacheableDecoratorDaoService.class,
        CorporationCrudDecoratorDaoService.class,
        CorporationThrowableDecoratorDaoService.class})
public class CorporationDaoService extends AbstractDaoService<String, Corporation> {
    @Override
    public Corporation create() {
        return new Corporation();
    }
}
