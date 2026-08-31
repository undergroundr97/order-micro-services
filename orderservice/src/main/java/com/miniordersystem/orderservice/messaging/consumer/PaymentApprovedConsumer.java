package com.miniordersystem.orderservice.messaging.consumer;

import com.miniordersystem.orderservice.messaging.event.PaymentAccepetedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentApprovedConsumer {


    @RabbitListener(
            queues = "payment.approved.order.queue"
    )
    public void consume(PaymentAccepetedEvent event){
        System.out.println(event);
    }
}
