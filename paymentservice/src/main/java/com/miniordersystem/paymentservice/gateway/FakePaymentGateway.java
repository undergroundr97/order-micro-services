package com.miniordersystem.paymentservice.gateway;


import java.math.BigDecimal;

public interface FakePaymentGateway {

    boolean process(BigDecimal amount);

}
