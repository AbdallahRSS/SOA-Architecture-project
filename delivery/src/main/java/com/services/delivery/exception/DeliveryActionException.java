package com.services.delivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when an invalid delivery action is attempted
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DeliveryActionException extends RuntimeException {

    public DeliveryActionException(String message) {
        super(message);
    }
}
