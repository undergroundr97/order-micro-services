package com.miniordersystem.orderservice.messaging.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentAccepetedEvent(
        Long paymentId,
        Long orderId,
        BigDecimal amount,
        LocalDateTime processedAt
) {
}
