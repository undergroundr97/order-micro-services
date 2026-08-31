package com.miniordersystem.orderservice.messaging.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentRejectdEvent(
        Long paymentId,
        Long orderId,
        BigDecimal amount,
        LocalDateTime processedAt,
        String reason
) {
}
