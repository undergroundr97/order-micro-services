package com.miniordersystem.orderservice.mapper;

import com.miniordersystem.orderservice.domain.Order;
import com.miniordersystem.orderservice.dto.OrderResponse;
import com.miniordersystem.orderservice.dto.PatchOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toResponse(Order order);

    void patchOrder(PatchOrderRequest request, @MappingTarget Order order);
}