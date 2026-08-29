package com.miniordersystem.orderservice.messaging.producer;

import com.miniordersystem.orderservice.messaging.event.OrderCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventProducer {

    private static final String ORDER_EXCHANGE = "order.exchange";
    private static final String ORDER_CREATED_ROUTING_KEY = "order.created";

    private final RabbitTemplate rabbitTemplate;

    public OrderEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishOrderCreated(OrderCreatedEvent event){
        rabbitTemplate.convertAndSend(
                ORDER_EXCHANGE,
                ORDER_CREATED_ROUTING_KEY,
                event
        );
    }

}
