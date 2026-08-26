package com.miniordersystem.orderservice.mapper;

import com.miniordersystem.orderservice.domain.Order;
import com.miniordersystem.orderservice.dto.OrderResponse;
import com.miniordersystem.orderservice.dto.PatchOrderRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toResponse(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchOrder(PatchOrderRequest request, @MappingTarget Order order);
}