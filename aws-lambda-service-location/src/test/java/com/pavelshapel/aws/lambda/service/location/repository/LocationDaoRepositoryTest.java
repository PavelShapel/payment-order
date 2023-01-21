package com.pavelshapel.aws.lambda.service.location.repository;

import com.pavelshapel.aws.lambda.service.location.MockLocation;
import com.pavelshapel.aws.lambda.service.location.model.Location;
import com.pavelshapel.aws.lambda.service.location.model.LocationType;
import com.pavelshapel.aws.lambda.service.location.provider.OneStringProvider;
import com.pavelshapel.aws.spring.boot.starter.api.service.DbHandler;
import com.pavelshapel.core.spring.boot.starter.api.util.RandomUtils;
import com.pavelshapel.core.spring.boot.starter.api.util.StreamUtils;
import com.pavelshapel.jpa.spring.boot.starter.repository.DaoRepository;
import com.pavelshapel.test.spring.boot.starter.annotation.SpringBootTestProfileTest;
import com.pavelshapel.test.spring.boot.starter.container.AwsDynamoDBExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTestProfileTest
@ExtendWith(AwsDynamoDBExtension.class)
class LocationDaoRepositoryTest implements MockLocation {
    @Autowired
    private RandomUtils randomUtils;
    @Autowired
    private StreamUtils streamUtils;
    @Autowired
    private DaoRepository<String, Location> daoRepository;
    @Autowired
    private DbHandler dynamoDbHandler;

    @BeforeEach
    void setUp() {
        String tableName = Location.class.getSimpleName().toLowerCase();
        Optional.ofNullable(dynamoDbHandler.createDefaultTable(tableName))
                .orElseGet(() -> dynamoDbHandler.deleteAll(Location.class).toString());
    }

    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void findAll_WithValidParam_ShouldReturnEntities(String name) {
        LocationType randomisedEnum = randomUtils.getRandomisedEnum(LocationType.class)
                .orElseThrow(RuntimeException::new);
        Location mockLocation = getMockLocation(null, null, name, randomisedEnum);
        Location savedLocation = dynamoDbHandler.save(mockLocation);

        List<Location> locations = streamUtils.iterableToList(daoRepository.findAll());

        assertThat(locations)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .contains(savedLocation);
    }
}