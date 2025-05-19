package com.feitep.engsoft.unitAndIntegrationTest.service;

// FifteenPercentStrategy.java
public class FifteenPercentStrategy implements DiscountStrategy {
    public boolean supports(double amount) { return amount >= 1000; }
    public double discount(double amount) { return amount * 0.15; }
}
