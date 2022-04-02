package com.pavelshapel.aws.lambda.service.location.repository;

import com.pavelshapel.aws.lambda.service.location.MockLocation;
import com.pavelshapel.aws.lambda.service.location.model.Location;
import com.pavelshapel.aws.lambda.service.location.model.LocationType;
import com.pavelshapel.aws.lambda.service.location.provider.OneStringProvider;
import com.pavelshapel.core.spring.boot.starter.api.util.RandomUtils;
import com.pavelshapel.core.spring.boot.starter.api.util.StreamUtils;
import com.pavelshapel.test.spring.boot.starter.layer.AbstractDynamoDbDaoRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class LocationDaoRepositoryTest extends AbstractDynamoDbDaoRepositoryTest<String, Location> implements MockLocation {
    @Autowired
    private RandomUtils randomUtils;
    @Autowired
    private StreamUtils streamUtils;

    @BeforeEach
    void setUp() {
        String tableName = Location.class.getSimpleName().toLowerCase();
        Optional.ofNullable(createTableIfNotExists(tableName))
                .orElseGet(() -> deleteAll(Location.class).toString());
    }

    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void findAll_WithValidParam_ShouldReturnListWithEntity(String name) {
        LocationType randomisedEnum = randomUtils.getRandomisedEnum(LocationType.class)
                .orElseThrow(RuntimeException::new);
        Location mockLocation = getMockLocation(null, null, name, randomisedEnum);
        Location savedLocation = save(mockLocation);

        List<Location> locations = streamUtils.iterableToList(getDaoRepository().findAll());

        assertThat(locations)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .contains(savedLocation);
    }
}