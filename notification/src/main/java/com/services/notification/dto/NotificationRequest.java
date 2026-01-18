package com.services.notification.dto;

import lombok.Data;

@Data
public class NotificationRequest {

    // Related order ID
    private Long orderId;

    // Notification type
    private String type;

    // Notification message
    private String message;
}
