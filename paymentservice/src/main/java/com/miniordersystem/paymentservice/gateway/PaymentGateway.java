package com.miniordersystem.paymentservice.gateway;


import java.math.BigDecimal;

public interface PaymentGateway {

    boolean process(BigDecimal amount);

}
