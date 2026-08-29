package com.miniordersystem.paymentservice.gateway;

import java.math.BigDecimal;

public class PaymentGateway implements FakePaymentGateway{


    @Override
    public boolean process(BigDecimal amount) {
        return true;
    }

    
}
