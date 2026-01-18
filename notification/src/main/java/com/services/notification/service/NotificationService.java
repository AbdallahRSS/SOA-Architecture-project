package com.services.notification.service;

import com.services.notification.dto.NotificationRequest;
import com.services.notification.entity.Notification;
import com.services.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;

    // Create and save a new notification
    public Notification create(NotificationRequest request) {

        Notification notification = Notification.builder()
                .orderId(request.getOrderId())
                .type(request.getType())
                .message(request.getMessage())
                .createdAt(LocalDateTime.now())
                .build();

        // Log notification for demo purpose
        System.out.println("🔔 Notification sent: " + notification.getMessage());

        return repository.save(notification);
    }

    // Get all notifications
    public List<Notification> findAll() {
        return repository.findAll();
    }

    // Get notification by ID
    public Notification findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
    }
}
