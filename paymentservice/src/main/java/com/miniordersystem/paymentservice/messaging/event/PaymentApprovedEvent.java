package com.miniordersystem.paymentservice.messaging.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentApprovedEvent(
        Long paymentId,
        Long orderId,
        BigDecimal amount,
        LocalDateTime processedAt
) {
}
