package com.feitep.engsoft.unitAndIntegrationTest.service;


import com.feitep.engsoft.unitAndIntegrationTest.entity.Order;
import com.feitep.engsoft.unitAndIntegrationTest.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DiscountServiceTest {
    @Mock
    OrderRepository orderRepository;
    DiscountService discountService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        discountService = new DiscountService(orderRepository);
    }

    @Test
    void testFourTiers() {
        assertEquals(0.0,   discountService.applyDiscount(50),   0.001);
        assertEquals(5.0,   discountService.applyDiscount(100),  0.001);
        assertEquals(50.0,  discountService.applyDiscount(500),  0.001);
        assertEquals(150.0, discountService.applyDiscount(1000), 0.001);
        verify(orderRepository, times(4)).save(any(Order.class));
    }
}