package com.miniordersystem.notificationservice.messaging.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentRejectedEvent(
        Long paymentId,
        Long orderId,
        BigDecimal amount,
        LocalDateTime processedAt,
        String reason
) {
}
