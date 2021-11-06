package com.pavelshapel.kafka.palmetto.kafka;

import com.pavelshapel.common.module.dto.kafka.Pizza;
import com.pavelshapel.common.module.dto.kafka.PizzaOrderDto;
import com.pavelshapel.common.module.dto.kafka.topic.KafkaTopic;
import com.pavelshapel.kafka.spring.boot.starter.service.KafkaConsumer;
import com.pavelshapel.kafka.spring.boot.starter.service.KafkaProducer;
import com.pavelshapel.test.spring.boot.starter.container.KafkaExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith({KafkaExtension.class})
class PizzaOrderKafkaConsumerTest {
    @SpyBean
    private KafkaProducer<PizzaOrderDto> kafkaProducer;
    @SpyBean
    private KafkaConsumer<PizzaOrderDto> kafkaConsumer;

    @ParameterizedTest
    @EnumSource(Pizza.class)
    void receive_ShouldReceiveMessage(Pizza pizza) {
        PizzaOrderDto mockPizzaOrderDto = createMockPizzaOrderDto(pizza.name());

        kafkaProducer.send(KafkaTopic.ORDER.getTopic().name(), mockPizzaOrderDto)
                .addCallback(
                        success -> verify(kafkaConsumer, times(1)).receive(any()),
                        fail -> {
                            throw new RuntimeException(fail);
                        });
    }

    private PizzaOrderDto createMockPizzaOrderDto(String pizza) {
        PizzaOrderDto pizzaOrderDto = new PizzaOrderDto();
        pizzaOrderDto.setId(1L);
        pizzaOrderDto.setPizza(pizza);
        pizzaOrderDto.setOrderTime(new Date());
        return pizzaOrderDto;
    }
}