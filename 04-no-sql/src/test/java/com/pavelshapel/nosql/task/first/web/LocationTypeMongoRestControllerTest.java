package com.pavelshapel.nosql.task.first.web;

import com.pavelshapel.jpa.spring.boot.starter.service.jpa.JpaService;
import com.pavelshapel.nosql.task.first.entity.LocationType;
import com.pavelshapel.nosql.task.first.repository.LocationTypeMongoRepository;
import com.pavelshapel.test.spring.boot.starter.container.postgres.MongoDBExtension;
import com.pavelshapel.test.spring.boot.starter.container.postgres.PostgreSQLExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({
        MongoDBExtension.class,
        PostgreSQLExtension.class
})
class LocationTypeMongoRestControllerTest {
    @Autowired
    private LocationTypeMongoRestController locationTypeMongoRestController;
    @Autowired
    private LocationTypeMongoRepository locationTypeMongoRepository;
    @Autowired
    private JpaService<LocationType> locationTypeJpaService;

    @BeforeEach
    void setUp() {
        locationTypeMongoRepository.deleteAll();
    }

    @Test
    void migrate_ShouldMigrateAllDataToMongo() {
        assertThat(locationTypeMongoRepository.findAll()).isEmpty();

        locationTypeMongoRestController.migrate();

        assertThat(locationTypeMongoRepository.findAll())
                .isNotEmpty()
                .hasSameSizeAs(locationTypeJpaService.findAll());
    }

    @Test
    void deleteAll_ShouldDeleteAllDataFromMongo() {
        locationTypeMongoRestController.migrate();

        locationTypeMongoRepository.deleteAll();

        assertThat(locationTypeMongoRepository.findAll())
                .isEmpty();
    }
}