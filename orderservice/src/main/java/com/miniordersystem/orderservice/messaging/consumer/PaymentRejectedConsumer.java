package com.miniordersystem.orderservice.messaging.consumer;

import com.miniordersystem.orderservice.messaging.event.PaymentRejectdEvent;
import com.miniordersystem.orderservice.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentRejectedConsumer {

    private final OrderService orderService;

    public PaymentRejectedConsumer(OrderService orderService) {
        this.orderService = orderService;
    }


    @RabbitListener(
            queues = "payment.rejected.order.queue"
    )
    public void consumer(PaymentRejectdEvent event){
        System.out.println(event);
        orderService.updateOrderStatus(event);
    }


}
