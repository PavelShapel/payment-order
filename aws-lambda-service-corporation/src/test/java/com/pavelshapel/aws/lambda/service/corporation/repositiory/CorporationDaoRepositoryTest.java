package com.pavelshapel.aws.lambda.service.corporation.repositiory;

import com.pavelshapel.aws.lambda.service.corporation.MockCorporation;
import com.pavelshapel.aws.lambda.service.corporation.handler.Handler;
import com.pavelshapel.aws.lambda.service.corporation.model.Corporation;
import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.aws.lambda.service.corporation.model.typed.AbstractTyped;
import com.pavelshapel.core.spring.boot.starter.api.util.StreamUtils;
import com.pavelshapel.test.spring.boot.starter.layer.AbstractDynamoDbDaoRepositoryTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.pavelshapel.core.spring.boot.starter.api.model.Dated.CREATED_DATE;
import static com.pavelshapel.core.spring.boot.starter.api.model.Dated.LAST_MODIFIED_DATE;
import static com.pavelshapel.core.spring.boot.starter.api.model.Entity.ID_FIELD;
import static com.pavelshapel.core.spring.boot.starter.api.model.Versioned.VERSION_FIELD;
import static org.assertj.core.api.Assertions.assertThat;

class CorporationDaoRepositoryTest extends AbstractDynamoDbDaoRepositoryTest<String, Corporation> implements MockCorporation {
    @Autowired
    private StreamUtils streamUtils;
    @Autowired
    private Handler typedHandler;

    @BeforeEach
    void setUp() {
        Class<Corporation> targetClass = Corporation.class;
        String tableName = targetClass.getSimpleName().toLowerCase();
        try {
            getDynamoDbHandler().createDefaultTable(tableName);
        } catch (Exception exception) {
            getDynamoDbHandler().deleteAll(targetClass);
        }
    }

    @ParameterizedTest
    @EnumSource(Type.class)
    void save_WithValidParam_ShouldSaveEntityAndFillInAuditableFields(Type type) {
        Corporation mockCorporation = typedHandler.getTyped(type)
                .map(typed -> getMockCorporation(null, null, typed))
                .orElseThrow(RuntimeException::new);

        Corporation savedCorporation = getDaoRepository().save(mockCorporation);

        assertThat(savedCorporation)
                .extracting(ID_FIELD)
                .isNotNull();
        assertThat(savedCorporation)
                .extracting(CREATED_DATE)
                .isNotNull();
        assertThat(savedCorporation)
                .extracting(LAST_MODIFIED_DATE)
                .isNotNull();
        assertThat(savedCorporation)
                .extracting(VERSION_FIELD)
                .isEqualTo(1L);
    }

    @ParameterizedTest
    @EnumSource(Type.class)
    void update_WithValidParam_ShouldUpdateEntityAndFillInAuditableFields(Type type) {
        Corporation mockCorporation = typedHandler.getTyped(type)
                .map(typed -> getMockCorporation(null, null, typed))
                .orElseThrow(RuntimeException::new);
        Corporation savedCorporation = getDynamoDbHandler().save(mockCorporation);
        Optional.of(savedCorporation)
                .map(Corporation::getTyped)
                .map(AbstractTyped.class::cast)
                .ifPresent(abstractTyped -> abstractTyped.setName(RandomStringUtils.randomAlphabetic(1, Byte.MAX_VALUE)));

        Corporation updatedCorporation = getDaoRepository().save(savedCorporation);

        assertThat(updatedCorporation)
                .extracting(ID_FIELD)
                .isEqualTo(savedCorporation.getId());
        assertThat(updatedCorporation)
                .extracting(CREATED_DATE)
                .isEqualTo(savedCorporation.getCreatedDate());
        assertThat(updatedCorporation)
                .extracting(LAST_MODIFIED_DATE)
                .isNotEqualTo(savedCorporation.getCreatedDate());
        assertThat(updatedCorporation)
                .extracting(VERSION_FIELD)
                .isEqualTo(2L);
    }

    @Test
    void saveAll_WithValidParam_ShouldSaveAndReturnEntities() {
        List<Corporation> corporations = Arrays.stream(Type.values())
                .map(type -> typedHandler.getTyped(type))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(typed -> getMockCorporation(null, null, typed))
                .collect(Collectors.toList());

        Iterable<Corporation> savedCorporations = getDaoRepository().saveAll(corporations);

        assertThat(savedCorporations)
                .asList()
                .hasSize(Type.values().length);
    }

    @ParameterizedTest
    @EnumSource(Type.class)
    void findById_WithValidParam_ShouldReturnEntity(Type type) {
        Corporation mockCorporation = typedHandler.getTyped(type)
                .map(typed -> getMockCorporation(null, null, typed))
                .orElseThrow(RuntimeException::new);
        Corporation savedCorporation = getDynamoDbHandler().save(mockCorporation);

        Optional<Corporation> retrievedCorporation = getDaoRepository().findById(savedCorporation.getId());

        assertThat(retrievedCorporation)
                .isNotEmpty()
                .hasValue(savedCorporation);
    }

    @Test
    void findAllById_WithValidParam_ShouldReturnEntities() {
        List<Corporation> corporations = Arrays.stream(Type.values())
                .map(type -> typedHandler.getTyped(type))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(typed -> getMockCorporation(null, null, typed))
                .collect(Collectors.toList());
        Iterable<Corporation> savedCorporations = getDaoRepository().saveAll(corporations);
        List<String> savedIds = streamUtils.iterableToStream(savedCorporations)
                .map(Corporation::getId)
                .collect(Collectors.toList());

        Iterable<Corporation> retrievedCorporations = getDaoRepository().findAllById(savedIds);

        assertThat(retrievedCorporations)
                .asList()
                .hasSize(Type.values().length);
    }
}