package com.miniordersystem.paymentservice.mapper;


import com.miniordersystem.paymentservice.domain.Payment;
import com.miniordersystem.paymentservice.messaging.event.PaymentApprovedEvent;
import com.miniordersystem.paymentservice.messaging.event.PaymentRejectedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface PaymentEventMapper {



    @Mapping(source = "id", target = "paymentId")
    @Mapping(target = "reason", constant = "LIMIT_EXCEED")
    PaymentRejectedEvent toRejectedEvent(Payment payment);



    @Mapping(source = "id", target = "paymentId")
    PaymentApprovedEvent toApprovedEvent(Payment payment);



}
