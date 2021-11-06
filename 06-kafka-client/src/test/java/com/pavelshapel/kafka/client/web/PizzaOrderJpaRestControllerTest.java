package com.pavelshapel.kafka.client.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavelshapel.common.module.dto.kafka.client.PizzaOrderDto;
import com.pavelshapel.common.module.dto.kafka.topic.KafkaTopic;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.JpaService;
import com.pavelshapel.kafka.client.entity.Pizza;
import com.pavelshapel.kafka.client.entity.PizzaOrder;
import com.pavelshapel.kafka.client.service.PizzaOrderJpaService;
import com.pavelshapel.kafka.spring.boot.starter.service.KafkaConsumer;
import com.pavelshapel.kafka.spring.boot.starter.service.KafkaProducer;
import com.pavelshapel.test.spring.boot.starter.container.KafkaExtension;
import com.pavelshapel.test.spring.boot.starter.container.PostgreSQLExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.List;

import static com.pavelshapel.kafka.client.web.PizzaOrderJpaRestController.HOME_PATH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({KafkaExtension.class, PostgreSQLExtension.class})
class PizzaOrderJpaRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper jsonConverter;
    @Autowired
    private JpaService<PizzaOrder> jpaService;
    @SpyBean
    private KafkaProducer<PizzaOrderDto> kafkaProducer;
    @SpyBean
    private KafkaConsumer<PizzaOrderDto> kafkaConsumer;

    @BeforeEach
    void setUp() {
        jpaService.deleteAll();
    }

    @ParameterizedTest
    @EnumSource(Pizza.class)
    void save_ShouldSaveEntityAndSendMessage(Pizza pizza) throws Exception {
        PizzaOrderDto mockPizzaOrderDto = createMockPizzaOrderDto(pizza.name());

        mockMvc.perform(MockMvcRequestBuilders
                        .post(HOME_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonConverter.writeValueAsString(mockPizzaOrderDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("pizza").value(mockPizzaOrderDto.getPizza()));

        List<PizzaOrder> pizzaOrders = jpaService.findAll();
        assertThat(pizzaOrders)
                .asList()
                .hasSize(1);
        verify(kafkaProducer, times(1)).send(any(), any());
    }

    @ParameterizedTest
    @EnumSource(Pizza.class)
    void receive_ShouldReceiveMessage(Pizza pizza) {
        PizzaOrderDto mockPizzaOrderDto = createMockPizzaOrderDto(pizza.name());
        PizzaOrder mockPizzaOrder = createMockPizzaOrder(pizza);
        PizzaOrderJpaService mockPizzaOrderJpaService = mock(PizzaOrderJpaService.class);
        doReturn(mockPizzaOrder).when(mockPizzaOrderJpaService).update(any(), any());

        kafkaProducer.send(KafkaTopic.NOTIFICATION.getTopic().name(), mockPizzaOrderDto)
                .addCallback(
                        success -> verify(kafkaConsumer, times(1)).receive(any()),
                        fail -> {
                            throw new RuntimeException(fail);
                        });
    }

    private PizzaOrderDto createMockPizzaOrderDto(String pizza) {
        PizzaOrderDto pizzaOrderDto = new PizzaOrderDto();
        pizzaOrderDto.setPizza(pizza);
        return pizzaOrderDto;
    }

    private PizzaOrder createMockPizzaOrder(Pizza pizza) {
        PizzaOrder pizzaOrder = new PizzaOrder();
        pizzaOrder.setId(1L);
        pizzaOrder.setPizza(pizza);
        pizzaOrder.setOrderTime(new Date());
        return pizzaOrder;
    }

}