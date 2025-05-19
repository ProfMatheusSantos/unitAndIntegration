package com.feitep.engsoft.unitAndIntegrationTest.service;

import com.feitep.engsoft.unitAndIntegrationTest.entity.Order;
import com.feitep.engsoft.unitAndIntegrationTest.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {

    private final OrderRepository repository;
    private final List<DiscountStrategy> strategies;

    public DiscountService(OrderRepository repository) {
        this.repository = repository;
        this.strategies = List.of(
                new NoDiscountStrategy(),
                new FivePercentStrategy(),
                new TenPercentStrategy(),
                new FifteenPercentStrategy()
        );
    }

    @Transactional
    public double applyDiscount(double total) {
        DiscountStrategy strat = strategies.stream()
                .filter(s -> s.supports(total))
                .findFirst()
                .orElseThrow();

        double discount = strat.discount(total);
        Order order = new Order(total - discount);
        repository.save(order);
        return discount;
    }
}

