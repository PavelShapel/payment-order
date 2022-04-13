package com.pavelshapel.aws.lambda.service.corporation.service.decorator;

import com.pavelshapel.aws.lambda.service.corporation.model.Corporation;
import com.pavelshapel.core.spring.boot.starter.impl.service.decorator.Decorator;
import com.pavelshapel.core.spring.boot.starter.impl.service.decorator.instance.AbstractThrowableDecoratorDaoService;

@Decorator
public class CorporationThrowableDecoratorDaoService extends AbstractThrowableDecoratorDaoService<String, Corporation> {
}
