package com.pavelshapel.elk.service;

import com.pavelshapel.elk.entity.Event;
import com.pavelshapel.elk.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event save(Event event) {
        log.info("save [{}]", event);
        return eventRepository.save(event);
    }

    public Optional<Event> findById(String id) {
        log.info("find by id [{}]", id);
        return eventRepository.findById(id);
    }

    public List<Event> findAll() {
        log.info("find all");
        return StreamSupport.stream(eventRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void deleteAll() {
        log.info("delete all");
        eventRepository.deleteAll();
    }
}
