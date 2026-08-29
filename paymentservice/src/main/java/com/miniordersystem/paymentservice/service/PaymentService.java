package com.miniordersystem.paymentservice.service;

import com.miniordersystem.paymentservice.domain.Payment;
import com.miniordersystem.paymentservice.domain.PaymentStatus;
import com.miniordersystem.paymentservice.gateway.FakePaymentGateway;
import com.miniordersystem.paymentservice.gateway.PaymentGateway;
import com.miniordersystem.paymentservice.messaging.event.OrderCreatedEvent;
import com.miniordersystem.paymentservice.messaging.event.PaymentEvent;
import com.miniordersystem.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentGateway paymentGateway;

    public PaymentService(PaymentRepository paymentRepository, PaymentGateway paymentGateway) {
        this.paymentRepository = paymentRepository;
        this.paymentGateway = paymentGateway;
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

        Payment processedPayment = updateProcessPayment(payment);
        paymentRepository.save(processedPayment);



    }

    private Payment updateProcessPayment(Payment payment){
        boolean paymentAfterProcess = paymentGateway.process(payment.getAmount());
        payment.setStatus(currentStatus(paymentAfterProcess));
        payment.setProcessedAt(LocalDateTime.now());
        return payment;
    }

    private PaymentStatus currentStatus(boolean status){
        if(status){
            return PaymentStatus.APPROVED;
        } else {
            return PaymentStatus.REJECTED;
        }
    }


    private PaymentEvent generateEvent(Payment payment){


    }

}
