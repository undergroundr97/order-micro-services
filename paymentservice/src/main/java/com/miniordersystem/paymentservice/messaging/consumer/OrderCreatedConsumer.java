package com.miniordersystem.paymentservice.messaging.consumer;

import com.miniordersystem.paymentservice.messaging.event.OrderCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component

public class OrderCreatedConsumer {


    @RabbitListener(
            queues = "order.created.payment.queue"
    )
    public void consume(OrderCreatedEvent event){

        System.out.println("Event: " + event);
    }


}
