//package com.pavelshapel.service.location.repository;
//
//import com.pavelshapel.random.spring.boot.starter.StarterAutoConfiguration;
//import com.pavelshapel.service.location.ServiceLocationApplication;
//import com.pavelshapel.service.location.entity.LocationType;
//import com.pavelshapel.service.location.provider.String1Provider;
//import com.pavelshapel.stream.spring.boot.starter.util.StreamUtils;
//import com.pavelshapel.test.spring.boot.starter.container.postgres.AbstractPostgreSQLContainer;
//import com.pavelshapel.test.spring.boot.starter.container.postgres.CustomPostgreSQLContainer;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.context.ApplicationContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
//
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = NONE) //Don't replace the application default DataSource.
//@ContextConfiguration(
//        classes = {
//                ServiceLocationApplication.class,
//                String1Provider.class,
//                StarterAutoConfiguration.class,
//                StreamUtils.class
//        }
//)
////@ExtendWith(MockitoExtension.class)
////@TestInstance(PER_CLASS)
//class LocationTypeJpaRepositoryTest extends AbstractPostgreSQLContainer {
//    @Container
//    private static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER =
//            CustomPostgreSQLContainer.getInstance().withInitScript("test.sql");
//
//    @Autowired
//    private TestEntityManager testEntityManager;
//
//    @Autowired
//    private LocationTypeJpaRepository locationTypeJpaRepository;
//
//    @Autowired
//    private ApplicationContext context;
//
//    @Test
//    void initialization() {
//        assertThat(testEntityManager).isNotNull();
//        assertThat(POSTGRE_SQL_CONTAINER).isNotNull();
//        assertThat(context).isNotNull();
//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        System.out.println(beanDefinitionNames);
//    }
//
//    @Test
//        //@ArgumentsSource(String1Provider.class)
//    void findByNameContaining_WithValidParam_ShouldSaveAndReturnEntity() {
//        String name = "qwwqw";
//        LocationType locationType = getLocationType();
//        locationType.setName(name);
//        LocationType savedLocation = testEntityManager.persistFlushFind(locationType);
//
//        List<LocationType> fundedLocation = locationTypeJpaRepository.findByNameIgnoreCaseContaining(name.toUpperCase());
//
//        assertThat(fundedLocation)
//                .isNotNull()
//                .asList()
//                .isNotEmpty()
//                .contains(savedLocation);
//    }
////
////    @ParameterizedTest
////    @ArgumentsSource(String1Provider.class)
////    void findByNameContaining_WithInvalidParam_ShouldSaveAndReturnEmptyList(String name) {
////        final Location location = getLocationType();
////        location.setName(name);
////        final Location savedLocation = testEntityManager.persistFlushFind(location);
////
////
////        final List<Location> fundedLocation = locationJpaRepository.findByNameIgnoreCaseContaining("test");
////
////
////        assertThat(fundedLocation).isNotNull().asList().doesNotContain(savedLocation).isEmpty();
////    }
//
//    private LocationType getLocationType() {
//        return new LocationType();
//    }
//}