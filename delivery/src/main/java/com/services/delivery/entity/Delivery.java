package com.services.delivery.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Delivery entity represents a delivery task
 */
@Entity
@Table(name = "deliveries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Reference to order-service order ID
    @Column(nullable = false)
    private Long orderId;

    // Driver name (simulated)
    private String driverName;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    // Simulated GPS coordinates
    private Double latitude;
    private Double longitude;

    private LocalDateTime createdAt;
}
