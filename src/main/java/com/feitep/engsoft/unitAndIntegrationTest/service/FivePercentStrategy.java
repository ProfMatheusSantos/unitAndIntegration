package com.feitep.engsoft.unitAndIntegrationTest.service;

// FivePercentStrategy.java
public class FivePercentStrategy implements DiscountStrategy {
    public boolean supports(double amount) { return amount >= 100 && amount < 500; }
    public double discount(double amount) { return amount * 0.05; }
}
