package com.services.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderActionException extends RuntimeException {
    public OrderActionException(String message) {
        super(message);
    }
}
