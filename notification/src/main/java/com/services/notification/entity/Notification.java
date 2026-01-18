package com.services.notification.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    // Notification type: ORDER_CONFIRMED, ORDER_DELIVERED
    private String type;

    // Human-readable message
    private String message;

    // Creation timestamp
    private LocalDateTime createdAt;
}
