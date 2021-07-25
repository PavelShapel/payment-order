//package com.pavelshapel.service.location.controller;
//
//import com.pavelshapel.service.location.repository.LocationJpaRepository;
//import com.pavelshapel.service.location.service.LocationJpaService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.mock.mockito.SpyBean;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.Arrays;
//
//import static com.pavelshapel.common.module.controller.AbstractJpaRestController.PAGING_PATH;
//import static com.pavelshapel.service.location.controller.LocationJpaRestController.HOME_PATH;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.Mockito.verify;
//
//@WebMvcTest(controllers = LocationJpaRestController.class)
//@ExtendWith(MockitoExtension.class)
//class LocationJpaRestControllerTest {
//
//    @Captor
//    private ArgumentCaptor<Pageable> pageableArgumentCaptor;
//
//    @MockBean
//    private LocationJpaRepository jpaRepository;
//
//    @SpyBean
//    private LocationJpaService jpaService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//
//    @BeforeEach
//    void setUp() {
//        //when(jpaRepository.findAll(pageableArgumentCaptor.capture())).thenReturn(Page.empty());
//        //  when(jpaRepository.save(any(Country.class))).thenReturn(any(Country.class));
//    }
//
//    @Test
//    void initialization() {
//        assertThat(jpaRepository).isNotNull();
//        assertThat(jpaService).isNotNull();
//        assertThat(mockMvc).isNotNull();
//    }
//
//    @Test
//    void post_WithValidParams_ShouldReturnPage() throws Exception {
//        MvcResult mvcResult = mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post(HOME_PATH))
//                .andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertThat(status).isEqualTo(HttpStatus.OK.value());
//    }
//
//
//    @Test
//    void get_WithValidParams_ShouldReturnPage() throws Exception {
//        MvcResult mvcResult = mockMvc
//                .perform(MockMvcRequestBuilders
//                        .get(HOME_PATH + PAGING_PATH)
//                        .param("page", "5")
//                        .param("size", "10")
//                        .param("sort", "id,desc")
//                        .param("sort", "name,asc"))
//                .andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//
//        verify(jpaRepository).findAll(pageableArgumentCaptor.capture());
//        PageRequest pageable = (PageRequest) pageableArgumentCaptor.getValue();
//
//        assertThat(status).isEqualTo(HttpStatus.OK.value());
//        assertThat(pageable.getPageNumber()).isEqualTo(5);
//        assertThat(pageable.getPageSize()).isEqualTo(10);
//        assertThat(pageable.getSort()).isEqualTo(Sort.by(Arrays.asList(Sort.Order.desc("id"), Sort.Order.asc("name"))));
//    }
//}