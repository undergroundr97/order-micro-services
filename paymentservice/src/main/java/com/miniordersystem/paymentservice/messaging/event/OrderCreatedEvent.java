package com.miniordersystem.paymentservice.messaging.event;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record OrderCreatedEvent(
    Long orderId,
    BigDecimal total,
    LocalDateTime createdAt
) {
}
