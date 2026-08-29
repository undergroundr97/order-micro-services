package com.miniordersystem.orderservice.mapper;

import com.miniordersystem.orderservice.domain.Order;
import com.miniordersystem.orderservice.messaging.event.OrderCreatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface OrderEventMapper {

    @Mapping(source = "id", target = "orderId")
    OrderCreatedEvent toOrderCreatedEvent(Order order);

}
