package com.miniordersystem.orderservice.messaging.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.converter.JacksonJsonMessageConverter;

@Configuration
public class RabbitMQConfig {


    @Bean
    public Queue orderCreatedQueue(){
        return new Queue("order.created.payment.queue", true);
    }

    @Bean
    public DirectExchange orderExchange(){
        return new DirectExchange("order.exchange");
    }

    @Bean
    public Binding orderCreatedBinding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("order.created");
    }

    @Bean
    public JacksonJsonMessageConverter jsonMessageConverter(){
        return new JacksonJsonMessageConverter();
    }


}
