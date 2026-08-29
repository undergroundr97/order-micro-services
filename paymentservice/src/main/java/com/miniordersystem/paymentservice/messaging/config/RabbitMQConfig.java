package com.miniordersystem.paymentservice.messaging.config;



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
    public JacksonJsonMessageConverter jsonMessageConverter(){
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public DirectExchange paymentExchange(){
        return new DirectExchange("payment.exchange");
    }

    @Bean
    public Queue approvedQueue(){
        return new Queue("payment.approved.order.queue");
    }

    @Bean
    public Queue rejectedQueue(){
        return new Queue("payment.rejected.order.queue");
    }


    @Bean
    public Binding paymentApprovedBinding(@Qualifier("approvedQueue") Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("payment.approved");
    }

    @Bean
    public Binding paymentRejectedBinding(@Qualifier("rejectedQueue") Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("payment.rejected");
    }


}
