package com.feitep.engsoft.unitAndIntegrationTest.service;


public interface DiscountStrategy {
    boolean supports(double amount);
    double discount(double amount);
}

