package com.miniordersystem.orderservice.service;


import com.miniordersystem.orderservice.domain.Order;
import com.miniordersystem.orderservice.domain.OrderStatus;
import com.miniordersystem.orderservice.dto.CreateOrderRequest;
import com.miniordersystem.orderservice.dto.OrderResponse;
import com.miniordersystem.orderservice.repository.OrderRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public OrderResponse createOrder(CreateOrderRequest orderRequest){
        LocalDateTime dateTimeNow = LocalDateTime.now();
        Order order = new Order(null, orderRequest.customerName(), OrderStatus.PENDING, orderRequest.total(), dateTimeNow);
        orderRepository.save(order);
        return new OrderResponse(order.getId(), order.getCustomerName(), order.getStatus(), order.getTotal(),
                order.getCreatedAt());
    }


    public OrderResponse findOrder(Long id){
        Order order =  orderRepository.findById(id).orElseThrow();

        return new OrderResponse(order.getId(), order.getCustomerName(), order.getStatus(), order.getTotal(),
                order.getCreatedAt());
    }

    public List<OrderResponse> findAll(){

        List<Order> orders  = orderRepository.findAll();
        List<OrderResponse> orderResponses = orders.stream().map(order ->
                new OrderResponse(
                order.getId(),
                order.getCustomerName(),
                order.getStatus(),
                order.getTotal(),
                order.getCreatedAt())
                ).toList();
        return orderResponses;
    }

    public void deleteOrder(Long id){
        Order order = orderRepository.findById(id).orElseThrow();
        try {
            orderRepository.delete(order);
        } catch (DataIntegrityViolationException e){
            e.getMessage();
        }

    }

}
