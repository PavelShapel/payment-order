package com.pavelshapel.service.location.service;

import com.pavelshapel.service.location.entity.LocationType;
import com.pavelshapel.service.location.MockLocationType;
import com.pavelshapel.service.location.provider.OneStringProvider;
import com.pavelshapel.service.location.repository.LocationTypeJpaRepository;
import com.pavelshapel.test.spring.boot.starter.layer.AbstractJpaServiceTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

class LocationTypeJpaServiceTest extends AbstractJpaServiceTest<LocationType> implements MockLocationType {
    @Mock
    private LocationTypeJpaRepository locationTypeJpaRepository;
    @InjectMocks
    private LocationTypeJpaService locationTypeJpaService;

    @Test
    void create_WithoutParam_ShouldReturnEntity() {
        LocationType locationType = locationTypeJpaService.create();

        assertThat(locationType)
                .isNotNull()
                .isInstanceOf(LocationType.class);
    }

    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void createAndSave_WithoutParam_ShouldSaveAndReturnEntity(String name) {
        LocationType mockLocationType = getMockLocationType(name);
        doReturn(mockLocationType).when(locationTypeJpaRepository).save(any(LocationType.class));

        LocationType savedLocationType = locationTypeJpaService.createAndSave();

        assertThat(savedLocationType)
                .isNotNull()
                .isInstanceOf(LocationType.class)
                .isEqualTo(mockLocationType);
    }

    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void save_WithValidParam_ShouldSaveAndReturnEntity(String name) {
        LocationType mockLocationType = getMockLocationType(name);
        doReturn(mockLocationType).when(locationTypeJpaRepository).save(any(LocationType.class));

        LocationType savedLocationType = locationTypeJpaService.save(mockLocationType);

        assertThat(savedLocationType)
                .isNotNull()
                .isInstanceOf(LocationType.class)
                .isEqualTo(mockLocationType);
    }

    @Test
    void save_WithNullParam_ShouldThrowException() {
        doThrow(IllegalArgumentException.class).when(locationTypeJpaRepository).save(null);

        assertThatThrownBy(() -> locationTypeJpaService.save(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}