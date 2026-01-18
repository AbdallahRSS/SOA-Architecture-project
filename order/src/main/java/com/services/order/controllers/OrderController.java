package com.services.order.controllers;

import com.services.order.entity.Order;
import com.services.order.entity.OrderStatus;
import com.services.order.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    // Create new order
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@Valid @RequestBody Order order) {
        return service.createOrder(order);
    }

    // Get all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return service.getAllOrders();
    }

    // Get order by ID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return service.getOrderById(id);
    }

    // Cancel order
    @PutMapping("/{id}/cancel")
    public Order cancelOrder(@PathVariable Long id) {
        return service.cancelOrder(id);
    }

    // Delete order
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
    }

    // New endpoint → mark order as CONFIRMED (called by delivery-service)
    @PutMapping("/{id}/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmOrder(@PathVariable Long id) {
        Order order = service.getOrderById(id);
        if (order.getStatus() == OrderStatus.CREATED) {
            order.setStatus(OrderStatus.CONFIRMED);
            service.createOrder(order); // save changes
        }
    }

    // New endpoint → mark order as DELIVERED (called by delivery-service)
    @PutMapping("/{id}/deliver")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliverOrder(@PathVariable Long id) {
        Order order = service.getOrderById(id);
        if (order.getStatus() == OrderStatus.CONFIRMED || order.getStatus() == OrderStatus.CREATED) {
            order.setStatus(OrderStatus.DELIVERED);
            service.createOrder(order); // save changes
        }
    }
}
