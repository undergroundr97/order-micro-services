package com.miniordersystem.notificationservice.messaging.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {


    @Bean
    public JacksonJsonMessageConverter jacksonJsonMessageConverter(){
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public DirectExchange paymentExchange(){
        return new DirectExchange("payment.exchange");
    }

    @Bean
    public Queue paymentApprovedQueue(){
        return new Queue("payment.approved.notification.queue");
    }

    @Bean
    public Queue paymentRejectedQueue(){
        return new Queue("payment.rejected.notification.queue");
    }

    @Bean
    public Binding approvedNotificationBinding(@Qualifier("paymentApprovedQueue")Queue queue, DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with("payment.approved");
    }

    @Bean
    public Binding rejectedNotificationBinding(@Qualifier("paymentRejectedQueue") Queue queue,
                                               DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with("payment.rejected");
    }



}
