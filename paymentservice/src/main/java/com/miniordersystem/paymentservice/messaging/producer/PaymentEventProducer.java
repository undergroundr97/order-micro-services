package com.miniordersystem.paymentservice.messaging.producer;


import com.miniordersystem.paymentservice.messaging.event.PaymentApprovedEvent;
import com.miniordersystem.paymentservice.messaging.event.PaymentRejectedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventProducer {

    private static final String PAYMENT_EXCHANGE = "payment.exchange";
    private static final String PAYMENT_APPROVED_ROUTING_KEY = "payment.aproved";
    private static final String PAYMENT_REJECTED_ROUTING_KEY = "payment.rejected";

    private final RabbitTemplate rabbitTemplate;

    public PaymentEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void publishApprovedPayment(PaymentApprovedEvent event){
        rabbitTemplate.convertAndSend(
                PAYMENT_EXCHANGE,
                PAYMENT_APPROVED_ROUTING_KEY,

        );

    }

    public void publishRejectedPayment(PaymentRejectedEvent event){
        rabbitTemplate.convertAndSend(
            PAYMENT_EXCHANGE,
                PAYMENT_REJECTED_ROUTING_KEY,

        );

    }


}
