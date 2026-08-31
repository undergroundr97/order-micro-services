package com.miniordersystem.paymentservice.service;

import com.miniordersystem.paymentservice.domain.Payment;
import com.miniordersystem.paymentservice.domain.PaymentStatus;
import com.miniordersystem.paymentservice.gateway.PaymentGateway;
import com.miniordersystem.paymentservice.mapper.PaymentEventMapper;
import com.miniordersystem.paymentservice.messaging.event.OrderCreatedEvent;
import com.miniordersystem.paymentservice.messaging.producer.PaymentEventProducer;
import com.miniordersystem.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentGateway paymentGateway;
    private final PaymentEventMapper paymentEventMapper;
    private final PaymentEventProducer paymentEventProducer;

    public PaymentService(PaymentRepository paymentRepository, PaymentGateway paymentGateway, PaymentEventMapper paymentEventMapper, PaymentEventProducer paymentEventProducer) {
        this.paymentRepository = paymentRepository;
        this.paymentGateway = paymentGateway;
        this.paymentEventMapper = paymentEventMapper;
        this.paymentEventProducer = paymentEventProducer;
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
       Payment savedPayment =  paymentRepository.save(payment);

        Payment processedPayment = updateProcessPayment(savedPayment);
        paymentRepository.save(processedPayment);

        generateEvent(processedPayment);

    }

    private Payment updateProcessPayment(Payment payment){
        boolean approved = paymentGateway.process(payment.getAmount());

        payment.setStatus(currentStatus(approved));
        payment.setProcessedAt(LocalDateTime.now());

        return payment;
    }

    private PaymentStatus currentStatus(boolean approved){
        return approved ? PaymentStatus.APPROVED : PaymentStatus.REJECTED;
    }


    private void generateEvent(Payment payment){


        switch (payment.getStatus()){
            case PaymentStatus.APPROVED -> {
                paymentEventProducer.publishApprovedPayment(paymentEventMapper.toApprovedEvent(payment));
            }
            case PaymentStatus.REJECTED -> {
                paymentEventProducer.publishRejectedPayment(paymentEventMapper.toRejectedEvent(payment));
            }
        }

    }

}
