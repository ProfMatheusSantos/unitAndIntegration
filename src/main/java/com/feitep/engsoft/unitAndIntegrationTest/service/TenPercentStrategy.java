package com.feitep.engsoft.unitAndIntegrationTest.service;

// TenPercentStrategy.java
public class TenPercentStrategy implements DiscountStrategy {
    public boolean supports(double amount) { return amount >= 500 && amount < 1000; }
    public double discount(double amount) { return amount * 0.10; }
}
