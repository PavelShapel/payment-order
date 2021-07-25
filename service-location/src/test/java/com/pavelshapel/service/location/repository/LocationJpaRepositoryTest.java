//package com.pavelshapel.service.location.repository;
//
//import com.pavelshapel.service.location.entity.Location;
//import com.pavelshapel.service.location.provider.String1Provider;
//import com.pavelshapel.test.spring.boot.starter.container.postgres.CustomPostgreSQLContainer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.ContextConfiguration;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
//
//
//@Testcontainers
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = NONE) //Don't replace the application default DataSource.
//@ContextConfiguration(
//        initializers = CustomPostgreSQLContainer.PostgreSQLInitializer.class
//)
//@ExtendWith(MockitoExtension.class)
//class LocationJpaRepositoryTest {
//    @Container
//    private static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER =
//            CustomPostgreSQLContainer.getInstance().withInitScript("test.sql");
//
//    @Autowired
//    private TestEntityManager testEntityManager;
//
//    @Autowired
//    private LocationJpaRepository locationJpaRepository;
//
//    @Test
//    void initialization() {
//        assertThat(testEntityManager).isNotNull();
//        assertThat(POSTGRE_SQL_CONTAINER).isNotNull();
//    }
//
//    @ParameterizedTest
//    @ArgumentsSource(String1Provider.class)
//    void findByNameContaining_WithValidParam_ShouldSaveAndReturnEntity(String name) {
//        final Location location = getLocation();
//        location.setName(name);
//        final Location savedLocation = testEntityManager.persistFlushFind(location);
//
//        final List<Location> fundedLocation = locationJpaRepository.findByNameIgnoreCaseContaining(name.toUpperCase());
//
//        assertThat(fundedLocation).isNotNull().asList().isNotEmpty().contains(savedLocation);
//    }
//
//    @ParameterizedTest
//    @ArgumentsSource(String1Provider.class)
//    void findByNameContaining_WithInvalidParam_ShouldSaveAndReturnEmptyList(String name) {
//        final Location location = getLocation();
//        location.setName(name);
//        final Location savedLocation = testEntityManager.persistFlushFind(location);
//
//
//        final List<Location> fundedLocation = locationJpaRepository.findByNameIgnoreCaseContaining("test");
//
//
//        assertThat(fundedLocation).isNotNull().asList().doesNotContain(savedLocation).isEmpty();
//    }
//
//    private Location getLocation() {
//        return new Location();
//    }
//}