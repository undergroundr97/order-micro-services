package com.miniordersystem.orderservice.service;


import com.miniordersystem.orderservice.domain.Order;
import com.miniordersystem.orderservice.domain.OrderStatus;
import com.miniordersystem.orderservice.dto.CreateOrderRequest;
import com.miniordersystem.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Order createOrder(CreateOrderRequest orderRequest){
        LocalDateTime dateTimeNow = LocalDateTime.now();
        Order order = new Order(null, orderRequest.customerName(), OrderStatus.PENDING, orderRequest.total(), dateTimeNow);
        orderRepository.save(order);
        return order;
    }


    public Order findOrder(Long id){
        return orderRepository.findById(id).orElseThrow();
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }


}
