package com.services.delivery.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Event published when a delivery is assigned
 */
@Getter
@RequiredArgsConstructor
public class DeliveryAssignedEvent {

    private final Long orderId;
    private final String driverName;
}
