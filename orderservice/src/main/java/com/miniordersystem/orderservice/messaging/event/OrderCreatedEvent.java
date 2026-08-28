package com.miniordersystem.orderservice.messaging.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderCreatedEvent(
        Long orderId,
        BigDecimal total,
        LocalDateTime createdAt
) {

}
