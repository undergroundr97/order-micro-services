package com.miniordersystem.orderservice.messaging.consumer;

import com.miniordersystem.orderservice.messaging.event.PaymentAccepetedEvent;
import com.miniordersystem.orderservice.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentApprovedConsumer {


    private final OrderService orderService;

    public PaymentApprovedConsumer(OrderService orderService){
        this.orderService = orderService;
    }

    @RabbitListener(
            queues = "payment.approved.order.queue"
    )
    public void consume(PaymentAccepetedEvent event){
        System.out.println(event);
        orderService.updateOrderStatus(event);
        
    }
}
