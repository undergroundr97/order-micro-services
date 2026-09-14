package com.miniordersystem.orderservice.messaging.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.converter.JacksonJsonMessageConverter;

@Configuration
public class RabbitMQConfig {


    @Bean
    public JacksonJsonMessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public DirectExchange orderExchange(){
        return new DirectExchange("order.exchange");
    }

    @Bean
    public Queue paymentApprovedQueue(){
        return new Queue("payment.approved.order.queue", true);
    }

    @Bean
    public Queue paymentRejectedQueue(){
        return new Queue("payment.rejected.order.queue", true);
    }


    @Bean
    public DirectExchange paymentExchange(){
        return new DirectExchange("payment.exchange");
    }


    @Bean
    public Binding paymentApprovedBindng(
            @Qualifier("paymentApprovedQueue") Queue queue,
            @Qualifier("paymentExchange") DirectExchange directExchange
    ){
        return BindingBuilder.bind(queue).to(directExchange).with("payment.approved");
    }


    @Bean
    public Binding paymentRejectedBinding(
            @Qualifier("paymentRejectedQueue") Queue queue,
            @Qualifier("paymentExchange") DirectExchange directExchange
    ){
        return BindingBuilder.bind(queue).to(directExchange).with("payment.rejected");
    }

}
