package com.services.delivery.service;

import com.services.delivery.entity.Delivery;
import com.services.delivery.entity.DeliveryStatus;
import com.services.delivery.exception.DeliveryActionException;
import com.services.delivery.exception.DeliveryNotFoundException;
import com.services.delivery.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String ORDER_SERVICE_URL = "http://order-service:8081/orders";

    public Delivery createDelivery(Long orderId) {
        Delivery delivery = Delivery.builder()
                .orderId(orderId)
                .status(DeliveryStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();

        return deliveryRepository.save(delivery);
    }

    public Delivery assignDelivery(Long id,
                                   String driverName,
                                   Double latitude,
                                   Double longitude) {

        Delivery delivery = getDelivery(id);
        if (delivery.getStatus() != DeliveryStatus.CREATED) {
            throw new DeliveryActionException("Delivery cannot be assigned");
        }

        delivery.setDriverName(driverName);
        delivery.setLatitude(latitude);
        delivery.setLongitude(longitude);
        delivery.setStatus(DeliveryStatus.ASSIGNED);

        deliveryRepository.save(delivery);

        // Synchronous update → set order status to CONFIRMED
        try {
            restTemplate.put(ORDER_SERVICE_URL + "/" + delivery.getOrderId() + "/confirm", null);
        } catch (Exception e) {
            System.err.println("Failed to update order status: " + e.getMessage());
        }

        return delivery;
    }

    public Delivery completeDelivery(Long id) {
        Delivery delivery = getDelivery(id);
        if (delivery.getStatus() != DeliveryStatus.ASSIGNED) {
            throw new DeliveryActionException("Delivery cannot be completed");
        }

        delivery.setStatus(DeliveryStatus.COMPLETED);
        deliveryRepository.save(delivery);

        // Synchronous update → set order status to DELIVERED
        try {
            restTemplate.put(ORDER_SERVICE_URL + "/" + delivery.getOrderId() + "/deliver", null);
        } catch (Exception e) {
            System.err.println("Failed to update order status: " + e.getMessage());
        }

        return delivery;
    }

    public Delivery getDelivery(Long id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new DeliveryNotFoundException(id));
    }

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }
}
