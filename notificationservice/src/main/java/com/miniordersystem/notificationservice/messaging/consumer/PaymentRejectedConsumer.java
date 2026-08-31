package com.miniordersystem.notificationservice.messaging.consumer;


import com.miniordersystem.notificationservice.messaging.event.PaymentRejectedEvent;
import com.miniordersystem.notificationservice.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentRejectedConsumer {

    private final NotificationService notificationService;


    public PaymentRejectedConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(
            queues = "payment.rejected.order.queue"
    )
    public void consume(PaymentRejectedEvent event){
        notificationService.processRejectedEvent(event);
    }

}
