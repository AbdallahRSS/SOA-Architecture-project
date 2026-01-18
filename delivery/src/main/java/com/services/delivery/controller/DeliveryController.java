package com.services.delivery.controller;

import com.services.delivery.dto.AssignDeliveryRequest;
import com.services.delivery.entity.Delivery;
import com.services.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public Delivery create(@RequestParam Long orderId) {
        return deliveryService.createDelivery(orderId);
    }

    @PutMapping("/{id}/assign")
    public Delivery assign(@PathVariable Long id,
                           @RequestBody AssignDeliveryRequest request) {
        return deliveryService.assignDelivery(
                id,
                request.getDriverName(),
                request.getLatitude(),
                request.getLongitude()
        );
    }

    @PutMapping("/{id}/complete")
    public Delivery complete(@PathVariable Long id) {
        return deliveryService.completeDelivery(id);
    }

    @GetMapping("/{id}")
    public Delivery get(@PathVariable Long id) {
        return deliveryService.getDelivery(id);
    }

    @GetMapping
    public List<Delivery> getAll() {
        return deliveryService.getAllDeliveries();
    }
}
