package com.miniordersystem.paymentservice.service;

import com.miniordersystem.paymentservice.domain.Payment;
import com.miniordersystem.paymentservice.domain.PaymentStatus;
import com.miniordersystem.paymentservice.gateway.FakePaymentGateway;
import com.miniordersystem.paymentservice.gateway.PaymentGateway;
import com.miniordersystem.paymentservice.messaging.event.OrderCreatedEvent;
import com.miniordersystem.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentGateway paymentGateway;

    public PaymentService(PaymentRepository paymentRepository, FakePaymentGateway fakePaymentGateway) {
        this.paymentRepository = paymentRepository;
        this.paymentGateway = fakePaymentGateway;
    }

    public void processPayment(OrderCreatedEvent order){
        Payment payment = new Payment(
                null,
                order.orderId(),
                order.total(),
                PaymentStatus.PENDING,
                LocalDateTime.now(),
                null
        );

        boolean currentStatus = paymentGateway.process(payment.getAmount());

        paymentRepository.save(payment);
    }

    private PaymentStatus currentStatus(boolean status){
        if(status){
            return PaymentStatus.APPROVED;
        } else {
            return PaymentStatus.REJECTED;
        }
    }


}
