package com.miniordersystem.orderservice.service;


import com.miniordersystem.orderservice.domain.Order;
import com.miniordersystem.orderservice.domain.OrderStatus;
import com.miniordersystem.orderservice.dto.CreateOrderRequest;
import com.miniordersystem.orderservice.dto.OrderResponse;
import com.miniordersystem.orderservice.dto.UpdateOrderRequest;
import com.miniordersystem.orderservice.mapper.OrderMapper;
import com.miniordersystem.orderservice.repository.OrderRepository;
import com.miniordersystem.orderservice.restcontroller.exception.customexception.OrderNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public OrderResponse createOrder(CreateOrderRequest orderRequest){
        LocalDateTime dateTimeNow = LocalDateTime.now();
        Order order = new Order(null, orderRequest.customerName(), OrderStatus.PENDING, orderRequest.total(), dateTimeNow);
        Order savedOrder = orderRepository.save(order);

        return OrderMapper.toResponse(savedOrder);
    }


    public OrderResponse findOrder(Long id){
        Order order =
                orderRepository.findById(id).orElseThrow( () -> new OrderNotFoundException("Order with id: " + id " " +
                        "was not found."));
        return OrderMapper.toResponse(order);
    }

    public List<OrderResponse> findAll(){

        List<Order> orders  = orderRepository.findAll();
        List<OrderResponse> orderResponses = orders
                        .stream()
                        .map(OrderMapper::toResponse)
                        .toList();

        return orderResponses;
    }

    public void deleteOrder(Long id){
        Order order = orderRepository.findById(id).orElseThrow( () -> new OrderNotFoundException("Order with " +
                "id: " + id + " was not found."));
        orderRepository.delete(order);
    }

    public OrderResponse updateOrder(Long id, UpdateOrderRequest request){
        Order order = orderRepository.findById(id).orElseThrow( () -> new OrderNotFoundException("Cannot find order " +
                "with id: " + id + "."));

        order.setCustomerName(request.customerName());
        order.setTotal(request.total());

        Order savedOrder = orderRepository.save(order);

        return OrderMapper.toResponse(savedOrder);
    }

}
