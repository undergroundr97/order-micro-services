package com.miniordersystem.orderservice.restcontroller.exception.customexception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
