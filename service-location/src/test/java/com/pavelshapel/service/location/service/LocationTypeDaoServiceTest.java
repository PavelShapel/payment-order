package com.pavelshapel.service.location.service;

import com.pavelshapel.service.location.MockLocationType;
import com.pavelshapel.service.location.model.LocationType;
import com.pavelshapel.service.location.provider.OneStringProvider;
import com.pavelshapel.service.location.repository.LocationTypeDaoRepository;
import com.pavelshapel.test.spring.boot.starter.layer.AbstractJpaDaoServiceTest;
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

class LocationTypeDaoServiceTest extends AbstractJpaDaoServiceTest<Long, LocationType> implements MockLocationType {
    @Mock
    private LocationTypeDaoRepository locationTypeDaoRepository;
    @InjectMocks
    private LocationTypeDaoService locationTypeDaoService;

    @Test
    void create_WithoutParam_ShouldReturnEntity() {
        LocationType locationType = locationTypeDaoService.create();

        assertThat(locationType)
                .isNotNull()
                .isInstanceOf(LocationType.class);
    }

    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void createAndSave_WithoutParam_ShouldSaveAndReturnEntity(String name) {
        LocationType mockLocationType = getMockLocationType(name);
        doReturn(mockLocationType).when(locationTypeDaoRepository).save(any(LocationType.class));

        LocationType savedLocationType = locationTypeDaoService.createAndSave();

        assertThat(savedLocationType)
                .isNotNull()
                .isInstanceOf(LocationType.class)
                .isEqualTo(mockLocationType);
    }

    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void save_WithValidParam_ShouldSaveAndReturnEntity(String name) {
        LocationType mockLocationType = getMockLocationType(name);
        doReturn(mockLocationType).when(locationTypeDaoRepository).save(any(LocationType.class));

        LocationType savedLocationType = locationTypeDaoService.save(mockLocationType);

        assertThat(savedLocationType)
                .isNotNull()
                .isInstanceOf(LocationType.class)
                .isEqualTo(mockLocationType);
    }

    @Test
    void save_WithNullParam_ShouldThrowException() {
        doThrow(IllegalArgumentException.class).when(locationTypeDaoRepository).save(null);

        assertThatThrownBy(() -> locationTypeDaoService.save(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}