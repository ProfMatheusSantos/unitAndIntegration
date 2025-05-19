package com.feitep.engsoft.unitAndIntegrationTest.repository;

import com.feitep.engsoft.unitAndIntegrationTest.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}
