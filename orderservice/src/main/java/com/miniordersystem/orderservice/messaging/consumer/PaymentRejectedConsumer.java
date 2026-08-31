package com.miniordersystem.orderservice.messaging.consumer;

import com.miniordersystem.orderservice.messaging.event.PaymentRejectdEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentRejectedConsumer {




    @RabbitListener(
            queues = "payment.rejected.order.queue"
    )
    public void consumer(PaymentRejectdEvent event){
        System.out.println(event);
    }


}
