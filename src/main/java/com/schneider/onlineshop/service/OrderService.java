package com.schneider.onlineshop.service;


import com.schneider.onlineshop.model.Order;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final List<Order> orderList = new ArrayList<>();

    public List<Order> getAllOrders() {
        return new ArrayList<>(orderList); // Возвращаем копию списка
    }

    public Optional<Order> getOrderById(Long id) {
        return orderList.stream()
                .filter(order -> order.getOrderId().equals(id))
                .findFirst();
    }

    public Order createOrder(Order order) {
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        order.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        orderList.add(order);
        return order;
    }

    public Optional<Order> updateOrder(Long id, Order updatedOrder) {
        return getOrderById(id).map(existingOrder -> {
            existingOrder.setUserId(updatedOrder.getUserId());
            existingOrder.setDeliveryAddress(updatedOrder.getDeliveryAddress());
            existingOrder.setContactPhone(updatedOrder.getContactPhone());
            existingOrder.setDeliveryMethod(updatedOrder.getDeliveryMethod());
            existingOrder.setStatus(updatedOrder.getStatus());
            existingOrder.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            return existingOrder;
        });
    }

    public boolean deleteOrder(Long id) {
        return orderList.removeIf(order -> order.getOrderId().equals(id));
    }
}
