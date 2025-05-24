package com.feitep.engsoft.unitAndIntegrationTest.integration;

import com.feitep.engsoft.unitAndIntegrationTest.entity.Order;
import com.feitep.engsoft.unitAndIntegrationTest.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DiscountControllerIntegrationTestSemH2 {
    @Autowired MockMvc mvc;

    @MockitoBean
    OrderRepository orderRepository;

    List<Order> mockOrders = new ArrayList<>();

    @BeforeEach
    void setup(){
        doAnswer(new Answer<Order>() {
            @Override
            public Order answer(InvocationOnMock invocation) throws Throwable {
                Order order = (Order) invocation.getArgument(0);
                mockOrders.add(order);
                return order;
            }
        }).when(orderRepository).save(any(Order.class));
    }

    @Test
    void shouldReturnDiscountAndPersistOrder() throws Exception {

        mvc.perform(get("/api/discount").param("amount","500").param("tier", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("50.0"));



        assertThat(mockOrders).hasSize(1);
        assertThat(mockOrders.get(0).getTotal()).isEqualTo(450.0);
    }
}