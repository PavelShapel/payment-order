package com.pavelshapel.service.location.repository;

import com.pavelshapel.service.location.entity.Country;
import com.pavelshapel.test.spring.boot.starter.container.postgres.CustomPostgreSQLContainer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;


@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ContextConfiguration(
        initializers = CustomPostgreSQLContainer.PostgreSQLInitializer.class
)
class CountryRepositoryTest {
    @Container
    private static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER =
            CustomPostgreSQLContainer.getInstance("schema.sql");

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CountryRepository countryRepository;

    @Test
    void initialization() {
        assertThat(testEntityManager).as("bean initialization").isNotNull();
        assertThat(POSTGRE_SQL_CONTAINER).as("bean initialization").isNotNull();
    }

    @ParameterizedTest
    @ArgumentsSource(String1Provider.class)
    void findByNameContaining_WithValidParam_ShouldSaveAndReturnEntity(String name) {
        //given
        final Country country = getCountry();
        country.setName(name);
        final Country savedCountry = testEntityManager.persistFlushFind(country);

        //when
        final List<Country> fundedCountry = countryRepository.findByNameContaining(name);

        //then
        then(fundedCountry).isNotNull().asList().isNotEmpty().contains(savedCountry);
    }

    @ParameterizedTest
    @ArgumentsSource(String1Provider.class)
    void findByNameContaining_WithInvalidParam_ShouldSaveAndReturnEmptyList(String name) {
        //given
        final Country country = getCountry();
        country.setName(name);
        final Country savedCountry = testEntityManager.persistFlushFind(country);

        //when
        final List<Country> fundedCountry = countryRepository.findByNameContaining("test");

        //then
        then(fundedCountry).isNotNull().asList().isEmpty();
    }

    private Country getCountry() {
        return new Country();
    }
}