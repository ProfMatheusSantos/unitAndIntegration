package com.feitep.engsoft.unitAndIntegrationTest.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double total;

    public Order() {}
    public Order(double total) { this.total = total; }

    public Long getId() { return id; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

}


