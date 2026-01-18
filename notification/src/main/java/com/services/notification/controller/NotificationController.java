package com.services.notification.controller;

import com.services.notification.dto.NotificationRequest;
import com.services.notification.entity.Notification;
import com.services.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    // Create a new notification
    @PostMapping
    public Notification create(@RequestBody NotificationRequest request) {
        return service.create(request);
    }

    // Get all notifications
    @GetMapping
    public List<Notification> getAll() {
        return service.findAll();
    }

    // Get notification by ID
    @GetMapping("/{id}")
    public Notification getById(@PathVariable Long id) {
        return service.findById(id);
    }
}
