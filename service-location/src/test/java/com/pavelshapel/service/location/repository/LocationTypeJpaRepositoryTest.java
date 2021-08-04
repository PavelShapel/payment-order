//package com.pavelshapel.service.location.repository;
//
//import com.pavelshapel.service.location.AbstractTest;
//import com.pavelshapel.service.location.entity.LocationType;
//import com.pavelshapel.service.location.provider.OneStringProvider;
//import com.pavelshapel.service.location.provider.TwoStringProvider;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.context.annotation.Import;
//
//import javax.validation.ConstraintViolationException;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//@DataJpaTest
//@Import(AnnotationAwareAspectJAutoProxyCreator.class)
//class LocationTypeJpaRepositoryTest extends AbstractTest {
//    @Autowired
//    private TestEntityManager testEntityManager;
//
//    @Autowired
//    private LocationTypeJpaRepository locationTypeJpaRepository;
//
//    @ParameterizedTest
//    @ArgumentsSource(OneStringProvider.class)
//    void findByNameIgnoreCaseContaining_WithValidParam_ShouldSaveAndReturnListWithEntity(String name) {
//        LocationType mockLocationType = getMockLocationType(name);
//        LocationType savedLocationType = testEntityManager.persistFlushFind(mockLocationType);
//
//        List<LocationType> retrievedLocationType = locationTypeJpaRepository.findByNameIgnoreCaseContaining(name.toUpperCase());
//
//        assertThat(retrievedLocationType)
//                .isNotNull()
//                .isNotEmpty()
//                .contains(savedLocationType);
//    }
//
//    @ParameterizedTest
//    @ArgumentsSource(TwoStringProvider.class)
//    void findByNameContaining_WithInvalidParam_ShouldSaveAndReturnEmptyList(String name, String searchName) {
//        LocationType mockLocationType = getMockLocationType(name);
//        testEntityManager.persistFlushFind(mockLocationType);
//
//        List<LocationType> retrievedLocationType = locationTypeJpaRepository.findByNameIgnoreCaseContaining(searchName);
//
//        assertThat(retrievedLocationType)
//                .isNotNull()
//                .isEmpty();
//    }
//
//    @Test
//    void findByNameContaining_WithNullParam_ShouldThrowException() {
//        assertThatThrownBy(() -> testEntityManager.persistFlushFind(getMockLocationType(null)))
//                .isInstanceOf(ConstraintViolationException.class);
//    }
//}