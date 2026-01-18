package com.services.notification.repository;

import com.services.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository for Notification entity
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
