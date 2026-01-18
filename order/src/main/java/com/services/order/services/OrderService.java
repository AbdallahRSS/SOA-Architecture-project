package com.services.order.services;

import com.services.order.entity.Order;
import com.services.order.entity.OrderStatus;
import com.services.order.exception.OrderActionException;
import com.services.order.exception.OrderNotFoundException;
import com.services.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final RestTemplate restTemplate;

    // Notification service (running locally, not docker)
    private static final String NOTIFICATION_URL = "http://notification-service:8083/notifications";

    public OrderService(OrderRepository repository) {
        this.repository = repository;
        this.restTemplate = new RestTemplate();
    }

    public Order createOrder(Order order) {
        if (order.getStatus() == null) {
            order.setStatus(OrderStatus.CREATED);
        }

        Order saved = repository.save(order);

        // Send notification (non-blocking logic)
        sendNotification(
                saved.getId(),
                "ORDER_CREATED",
                "Order " + saved.getId() + " has been created"
        );

        return saved;
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Order getOrderById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    public Order cancelOrder(Long id) {
        Order order = getOrderById(id);

        if (order.getStatus() != OrderStatus.CREATED) {
            throw new OrderActionException("Only CREATED orders can be cancelled");
        }

        order.setStatus(OrderStatus.CANCELLED);
        Order saved = repository.save(order);

        sendNotification(
                saved.getId(),
                "ORDER_CANCELLED",
                "Order " + saved.getId() + " has been cancelled"
        );

        return saved;
    }

    public void deleteOrder(Long id) {
        Order order = getOrderById(id);

        if (order.getStatus() != OrderStatus.CANCELLED) {
            throw new OrderActionException("Only CANCELLED orders can be deleted");
        }

        repository.delete(order);

        sendNotification(
                id,
                "ORDER_DELETED",
                "Order " + id + " has been deleted"
        );
    }

    // ===============================
    // Helper method (single responsibility)
    // ===============================
    private void sendNotification(Long orderId, String type, String message) {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("orderId", orderId);
            body.put("type", type);
            body.put("message", message);

            restTemplate.postForObject(NOTIFICATION_URL, body, Void.class);
        } catch (Exception e) {
            // Do NOT break order-service if notification fails
            System.err.println("Notification service unavailable: " + e.getMessage());
        }
    }
}
