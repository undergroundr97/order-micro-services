package com.miniordersystem.orderservice.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;


public record PatchOrderRequest (
        @Pattern(regexp = ".*\\S.*", message = "customerName must contain non-whitespace characters")
        String customerName,
        @Positive
        BigDecimal total
) {






}
