package com.pavelshapel.aws.lambda.service.nbrb.service.decorator;

import com.pavelshapel.aws.lambda.service.nbrb.model.Nbrb;
import com.pavelshapel.core.spring.boot.starter.impl.service.decorator.Decorator;
import com.pavelshapel.core.spring.boot.starter.impl.service.decorator.instance.AbstractCacheableDecoratorDaoService;
import com.pavelshapel.core.spring.boot.starter.impl.web.search.SearchCriterion;
import org.springframework.cache.Cache;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Decorator
public class NbrbCacheableDecoratorDaoService extends AbstractCacheableDecoratorDaoService<String, Nbrb> {
    @Override
    public List<Nbrb> findAll(Collection<SearchCriterion> searchCriteria) {
        return Optional.ofNullable(getCache())
                .map(Cache::getNativeCache)
                .filter(ConcurrentHashMap.class::isInstance)
                .map(ConcurrentHashMap.class::cast)
                .map(ConcurrentHashMap::values)
                .map(Collection::stream)
                .map(this::collectToList)
                .map(Collection::stream)
                .map(stream -> filterStream(searchCriteria, stream))
                .map(this::collectToList)
                .filter(list -> !list.isEmpty())
                .orElseGet(() -> super.findAll(searchCriteria));
    }

    private List<Nbrb> collectToList(Stream<?> stream) {
        return stream
                .filter(Nbrb.class::isInstance)
                .map(Nbrb.class::cast)
                .collect(Collectors.toList());
    }
}
