package com.pavelshapel.elk.repository;

import com.pavelshapel.elk.entity.Event;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends ElasticsearchRepository<Event, String> {
}