package com.miniordersystem.notificationservice.messaging.consumer;

import com.miniordersystem.notificationservice.messaging.event.PaymentApprovedEvent;
import com.miniordersystem.notificationservice.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentApprovedConsumer {

    private final NotificationService notificationService;

    public PaymentApprovedConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @RabbitListener(
            queues = "payment.approved.order.queue"
    )
    public void consume(PaymentApprovedEvent event){
        notificationService.processApprovedEvent(event);
    }


}
