package com.miniordersystem.orderservice.repository;

import com.miniordersystem.orderservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
