package com.feitep.engsoft.unitAndIntegrationTest.integration;

import com.feitep.engsoft.unitAndIntegrationTest.entity.Order;
import com.feitep.engsoft.unitAndIntegrationTest.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DiscountControllerIntegrationTest {
    @Autowired MockMvc mvc;

    //Nesse caso, para SQL há um banco de dados h2 configurado apenas para o ambiente de teste.
    @Autowired
    OrderRepository orderRepository;

    @Test
    void shouldReturnDiscountAndPersistOrder() throws Exception {
        mvc.perform(get("/api/discount").param("amount","500").param("tier", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("50.0"));

        List<Order> orders = orderRepository.findAll();
        assertThat(orders).hasSize(1);
        assertThat(orders.get(0).getTotal()).isEqualTo(450.0);
    }
}