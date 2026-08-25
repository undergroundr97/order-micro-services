package com.miniordersystem.orderservice.repository;

import com.miniordersystem.orderservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
