package com.pavelshapel.elk.web;

import com.pavelshapel.elk.entity.Event;
import com.pavelshapel.elk.entity.EventType;
import com.pavelshapel.elk.entity.SubEvent;
import com.pavelshapel.elk.web.provider.OneStringProvider;
import com.pavelshapel.test.spring.boot.starter.container.ElasticsearchExtension;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(ElasticsearchExtension.class)
class EventControllerTest {
    @Autowired
    private EventController eventController;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @BeforeEach
    void setUp() {
        elasticsearchRestTemplate.delete(Query.findAll(), Event.class);
    }

    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void save_ShouldSaveAndReturnEntity(String title) {
        Event mockEvent = createMockEvent(title);

        ResponseEntity<Event> response = eventController.save(mockEvent);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Event event = elasticsearchRestTemplate.get(response.getBody().getId(), Event.class);
        assertThat(event).isNotNull();
    }

    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void findById_ShouldReturnEntity(String title) {
        Event mockEvent = createMockEvent(title);
        Event savedEvent = elasticsearchRestTemplate.save(mockEvent);

        ResponseEntity<Event> response = eventController.findById(savedEvent.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(savedEvent).isEqualTo(response.getBody());
    }

    @SneakyThrows
    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void findAll_ShouldReturnAllEntities(String title) {
        Event mockEvent = createMockEvent(title);
        elasticsearchRestTemplate.save(mockEvent);

        ResponseEntity<List<Event>> response = eventController.findAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void deleteAll_ShouldDeleteAllEntities(String title) {
        Event mockEvent = createMockEvent(title);
        elasticsearchRestTemplate.save(mockEvent);

        ResponseEntity<Void> voidResponseEntity = eventController.deleteAll();

        assertThat(voidResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private Event createMockEvent(String title) {
        Event event = new Event();
        event.setTitle(title);
        event.setDescription(String.format("description_%s", title));
        EventType[] eventTypes = EventType.values();
        event.setEventType(eventTypes[ThreadLocalRandom.current().nextInt(eventTypes.length)]);
        List<SubEvent> subEvents = IntStream.range(0, ThreadLocalRandom.current().nextInt(5))
                .mapToObj(value -> createMockSubEvent(String.format("subEventName_%s", title)))
                .collect(Collectors.toList());
        event.setSubEvents(subEvents);
        return event;
    }

    private SubEvent createMockSubEvent(String name) {
        SubEvent subEvent = new SubEvent();
        subEvent.setName(name);
        return subEvent;
    }
}