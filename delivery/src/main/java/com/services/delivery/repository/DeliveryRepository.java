package com.services.delivery.repository;

import com.services.delivery.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for Delivery entity
 */
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
