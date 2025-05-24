package com.feitep.engsoft.unitAndIntegrationTest.controller;

import com.feitep.engsoft.unitAndIntegrationTest.service.DiscountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DiscountController {
    private final DiscountService service;
    public DiscountController(DiscountService service) { this.service = service; }

    @GetMapping("/discount")
    public double getDiscount(@RequestParam double amount, @RequestParam int tier) {
        return service.applyDiscount(amount, tier);
    }
}