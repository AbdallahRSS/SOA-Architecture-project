package com.services.delivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when delivery is not found
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DeliveryNotFoundException extends RuntimeException {

    public DeliveryNotFoundException(Long id) {
        super("Delivery with ID " + id + " not found");
    }
}
