package com.miniordersystem.paymentservice.messaging.consumer;

import com.miniordersystem.paymentservice.PaymentserviceApplication;
import com.miniordersystem.paymentservice.messaging.event.OrderCreatedEvent;
import com.miniordersystem.paymentservice.service.PaymentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component

public class OrderCreatedConsumer {


    private final PaymentService paymentService;


    public OrderCreatedConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RabbitListener(
            queues = "order.created.payment.queue"
    )
    public void consume(OrderCreatedEvent event){

        System.out.println(event);
        paymentService.processPayment(event);
    }


}
