package com.pavelshapel.elk.web;

import com.pavelshapel.elk.entity.Event;
import com.pavelshapel.elk.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@RestController
@RequestMapping("/events")
public class EventController {
    public static final String ID_PATH = "/{id}" + EMPTY;

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<Event> save(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.save(event));
    }

    @GetMapping(path = ID_PATH)
    public ResponseEntity<Event> findById(@PathVariable String id) {
        return eventService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
    }

    @GetMapping
    public ResponseEntity<List<Event>> findAll() {
        return ResponseEntity.ok(eventService.findAll());
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        eventService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
