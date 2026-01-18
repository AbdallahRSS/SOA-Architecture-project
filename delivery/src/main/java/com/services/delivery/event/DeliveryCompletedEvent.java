package com.services.delivery.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Event published when a delivery is completed
 */
@Getter
@RequiredArgsConstructor
public class DeliveryCompletedEvent {

    private final Long orderId;
}
