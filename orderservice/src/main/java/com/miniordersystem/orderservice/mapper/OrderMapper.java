package com.miniordersystem.orderservice.mapper;

import com.miniordersystem.orderservice.domain.Order;
import com.miniordersystem.orderservice.dto.OrderResponse;

public class OrderMapper {

    public static OrderResponse toResponse(Order order){
        return new OrderResponse(order.getId(), order.getCustomerName(), order.getStatus(), order.getTotal(), order.getCreatedAt());
    }

}