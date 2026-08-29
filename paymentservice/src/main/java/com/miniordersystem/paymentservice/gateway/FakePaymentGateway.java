package com.miniordersystem.paymentservice.gateway;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FakePaymentGateway implements PaymentGateway{


    @Override
    public boolean process(BigDecimal amount) {
        if(amount.doubleValue() < 1500){
            return true;
        } else {
            return false;
        }
    }


}
