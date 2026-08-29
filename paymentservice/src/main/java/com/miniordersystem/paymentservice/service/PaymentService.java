package com.miniordersystem.paymentservice.service;

import com.miniordersystem.paymentservice.domain.Payment;
import com.miniordersystem.paymentservice.domain.PaymentStatus;
import com.miniordersystem.paymentservice.messaging.event.OrderCreatedEvent;
import com.miniordersystem.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
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

        paymentRepository.save(payment);
    }


}
