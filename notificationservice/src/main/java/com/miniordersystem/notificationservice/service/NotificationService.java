package com.miniordersystem.notificationservice.service;


import com.miniordersystem.notificationservice.messaging.event.PaymentApprovedEvent;
import com.miniordersystem.notificationservice.messaging.event.PaymentRejectedEvent;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {


    public void processApprovedEvent(PaymentApprovedEvent event){
        System.out.println("Seu pedido de id: " + event.orderId() + " foi aprovado!");
    }

    public void processRejectedEvent(PaymentRejectedEvent event){
        System.out.println("Seu pedido de id: " + event.orderId() + " foi recusado. " +
                " Motivo: " + event.reason());
    }


}
