package com.miniordersystem.orderservice.dto;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public record PatchOrderRequest (
        String customerName,
        @Positive
        BigDecimal total
) {






}
