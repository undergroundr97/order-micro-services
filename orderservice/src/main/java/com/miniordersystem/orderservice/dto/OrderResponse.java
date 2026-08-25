package com.miniordersystem.orderservice.dto;

import com.miniordersystem.orderservice.domain.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponse(

        Long id,
        String customerName,
        OrderStatus status,
        BigDecimal total,
        LocalDateTime createdAt

) {
}

