package com.pavelshapel.service.location.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavelshapel.core.spring.boot.starter.CoreStarterAutoConfiguration;
import com.pavelshapel.service.location.MockLocationType;
import com.pavelshapel.service.location.model.LocationType;
import com.pavelshapel.service.location.provider.OneLongOneStringProvider;
import com.pavelshapel.service.location.provider.OneStringProvider;
import com.pavelshapel.service.location.repository.search.LocationTypeSearchSpecification;
import com.pavelshapel.service.location.service.LocationDaoService;
import com.pavelshapel.service.location.service.LocationTypeDaoService;
import com.pavelshapel.web.spring.boot.starter.WebStarterAutoConfiguration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.pavelshapel.service.location.web.LocationTypeDaoRestController.HOME_PATH;
import static com.pavelshapel.web.spring.boot.starter.web.AbstractDaoRestController.ID_PATH;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LocationTypeDaoRestController.class)
@Import({
        CoreStarterAutoConfiguration.class,
        LocationTypeSearchSpecification.class,
        WebStarterAutoConfiguration.class
})
class LocationTypeDaoRestControllerTest implements MockLocationType {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper jsonConverter;
    @MockBean
    private LocationTypeDaoService locationTypeDaoService;
    @MockBean
    private LocationDaoService locationDaoService;

    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void post_WithValidParam_ShouldCreateAndReturnEntity(String name) throws Exception {
        LocationType mockLocationType = getMockLocationType(name);
        doReturn(mockLocationType).when(locationTypeDaoService).save(any(LocationType.class));

        mockMvc.perform(MockMvcRequestBuilders
                        .post(HOME_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonConverter.writeValueAsString(mockLocationType))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name").value(mockLocationType.getName()));
    }

    @ParameterizedTest
    @ArgumentsSource(OneLongOneStringProvider.class)
    void put_WithValidParam_ShouldCreateAndReturnEntity(Long id, String name) throws Exception {
        LocationType mockLocationType = getMockLocationType(name);
        mockLocationType.setId(id);
        doReturn(mockLocationType).when(locationTypeDaoService).update(anyLong(), any(LocationType.class));
        doReturn(true).when(locationTypeDaoService).existsById(anyLong());

        mockMvc.perform(MockMvcRequestBuilders
                        .put(HOME_PATH + ID_PATH, String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonConverter.writeValueAsString(mockLocationType))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name").value(mockLocationType.getName()));
    }
}