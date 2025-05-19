package com.feitep.engsoft.unitAndIntegrationTest.service;

// NoDiscountStrategy.java
public class NoDiscountStrategy implements DiscountStrategy {
    public boolean supports(double amount) { return amount < 100; }
    public double discount(double amount) { return 0; }
}
