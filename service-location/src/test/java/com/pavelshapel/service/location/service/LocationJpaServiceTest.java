//package com.pavelshapel.service.location.service;
//
//import com.pavelshapel.common.module.repository.AbstractJpaRepository;
//import com.pavelshapel.json.spring.boot.starter.converter.JsonConverter;
//import com.pavelshapel.service.location.entity.Location;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class LocationJpaServiceTest {
//    @Autowired
//    private JsonConverter jsonConverter;
//
//    @Mock
//    private Location mockLocation;
//
//    @Mock
//    private AbstractJpaRepository<Location> jpaRepository;
//
//    @InjectMocks
//    private LocationJpaService jpaService;
//
//    private List<Location> mockCountries;
//
//    @Test
//    void initialization() {
//        assertThat(jpaRepository).isNotNull();
//        assertThat(jpaService).isNotNull();
//        assertThat(mockLocation).isNotNull();
//    }
//
//    @Test
//    void create_WithoutParam_ShouldReturnEntity() {
//        final Location location = jpaService.create();
//
//
//        assertThat(location).isNotNull().isInstanceOf(Location.class);
//    }
//
//    @Test
//    void createAndSave_WithoutParam_ShouldSaveAndReturnEntity() {
//        when(jpaRepository.save(any(Location.class))).thenReturn(mockLocation);
//
//
//        final Location savedLocation = jpaService.createAndSave();
//
//
//        assertThat(savedLocation).isNotNull().isInstanceOf(Location.class);
//        assertThat(savedLocation).isEqualTo(mockLocation);
//        verify(jpaRepository, times(1)).save(any(Location.class));
//    }
//
//    @Test
//    void save_WithValidParam_ShouldSaveAndReturnEntity() {
//        when(jpaRepository.save(any(Location.class))).thenReturn(mockLocation);
//
//
//        final Location savedLocation = jpaService.save(mockLocation);
//
//
//        assertThat(savedLocation).isNotNull().isInstanceOf(Location.class);
//        assertThat(savedLocation).isEqualTo(mockLocation);
//        verify(jpaRepository, times(1)).save(any(Location.class));
//    }
//
//    @Test
//    void save_WithNullParam_ShouldThrowException() {
//        when(jpaRepository.save(null)).thenThrow(new NullPointerException());
//
//
//        Assertions.assertThrows(NullPointerException.class, () -> jpaService.save(null));
//    }
//
//    @Test
//    void findById_WithValidParam_ShouldSaveAndReturnEntity() {
//        final long id = 1L;
//        mockLocation.setId(id);
//        when(jpaRepository.findById(id)).thenReturn(Optional.of(mockLocation));
//
//
//        final Location foundLocation = jpaService.findById(id);
//
//
//        assertThat(foundLocation).isNotNull().isInstanceOf(Location.class);
//        assertThat(foundLocation).isEqualTo(mockLocation);
//        verify(jpaRepository, times(1)).findById(any(Long.class));
//    }
//}