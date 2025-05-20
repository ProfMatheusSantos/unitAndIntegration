// src/main/java/com/feitep/engsoft/unitAndIntegrationTest/service/DiscountService.java
package com.feitep.engsoft.unitAndIntegrationTest.service;

import com.feitep.engsoft.unitAndIntegrationTest.entity.Order;
import com.feitep.engsoft.unitAndIntegrationTest.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    private final OrderRepository repository;

    public DiscountService(OrderRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public double applyDiscount(double total, int tier) {
        DiscountStrategy strat;

        switch (tier) {
            case 1:
                strat = new NoDiscountStrategy();
                break;
            case 2:
                strat = new FivePercentStrategy();
                break;
            case 3:
                strat = new TenPercentStrategy();
                break;
            case 4:
                strat = new FifteenPercentStrategy();
                break;
            default:
                throw new IllegalArgumentException("Tier inválido: deve ser entre 1 e 4");
        }

        double discount = strat.discount(total);
        Order order = new Order(total - discount);
        repository.save(order);
        return discount;
    }
}
