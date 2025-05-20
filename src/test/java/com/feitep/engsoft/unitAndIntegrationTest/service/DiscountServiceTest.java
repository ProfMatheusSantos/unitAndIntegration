// src/test/java/com/feitep/engsoft/unitAndIntegrationTest/service/DiscountServiceTest.java
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
        double d1 = discountService.applyDiscount(50, 1);
        assertEquals(0.0, d1, 0.001);

        double d2 = discountService.applyDiscount(100, 2);
        assertEquals(5.0, d2, 0.001);

        double d3 = discountService.applyDiscount(500, 3);
        assertEquals(50.0, d3, 0.001);

        double d4 = discountService.applyDiscount(1000, 4);
        assertEquals(150.0, d4, 0.001);

        verify(orderRepository, times(4)).save(any(Order.class));
    }

    @Test
    void testInvalidTierThrows() {
        assertThrows(IllegalArgumentException.class, () -> discountService.applyDiscount(200, 0));
        assertThrows(IllegalArgumentException.class, () -> discountService.applyDiscount(200, 5));
        verify(orderRepository, never()).save(any(Order.class));
    }
}
