package com.pavelshapel.service.location.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavelshapel.service.location.AbstractTest;
import com.pavelshapel.service.location.entity.LocationType;
import com.pavelshapel.service.location.provider.OneLongOneStringProvider;
import com.pavelshapel.service.location.provider.OneStringProvider;
import com.pavelshapel.service.location.service.LocationTypeJpaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.pavelshapel.service.location.controller.LocationTypeJpaRestController.HOME_PATH;
import static com.pavelshapel.web.spring.boot.starter.controller.AbstractJpaRestController.ID_PATH;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LocationTypeJpaRestController.class)
class LocationTypeJpaRestControllerTest extends AbstractTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper jsonConverter;

    @MockBean
    private LocationTypeJpaService locationTypeJpaService;

    @ParameterizedTest
    @ArgumentsSource(OneStringProvider.class)
    void post_WithValidParam_ShouldCreateAndReturnEntity(String name) throws Exception {
        LocationType mockLocationType = getMockLocationType(name);
        doReturn(mockLocationType).when(locationTypeJpaService).save(any(LocationType.class));

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

    @Test
    void post_WithNullParam_ShouldThrowException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(HOME_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @ArgumentsSource(OneLongOneStringProvider.class)
    void put_WithValidParam_ShouldCreateAndReturnEntity(Long id, String name) throws Exception {
        LocationType mockLocationType = getMockLocationType(name);
        mockLocationType.setId(id);
        doReturn(mockLocationType).when(locationTypeJpaService).update(anyLong(), any(LocationType.class));
        doReturn(true).when(locationTypeJpaService).existsById(anyLong());

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