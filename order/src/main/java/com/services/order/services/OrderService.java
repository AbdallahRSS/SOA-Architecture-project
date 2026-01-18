package com.services.order.services;

import com.services.order.entity.Order;
import com.services.order.entity.OrderStatus;
import com.services.order.exception.OrderActionException;
import com.services.order.exception.OrderNotFoundException;
import com.services.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order createOrder(Order order) {
        if (order.getStatus() == null) {
            order.setStatus(OrderStatus.CREATED);
        }
        return repository.save(order);
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
        return repository.save(order);
    }

    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        if (order.getStatus() != OrderStatus.CANCELLED) {
            throw new OrderActionException("Only CANCELLED orders can be deleted");
        }
        repository.delete(order);
    }
}
