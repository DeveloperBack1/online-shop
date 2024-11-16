package com.schneider.onlineshop.controller;

import com.schneider.onlineshop.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final List<Order> orderList = new ArrayList<>();

    @GetMapping
    public List<Order> getAllOrders() {
        return orderList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderList.stream().filter(o -> o.getOrderId().equals(id)).findFirst();
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        order.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        orderList.add(order);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        Optional<Order> existingOrder = orderList.stream().filter(o -> o.getOrderId().equals(id)).findFirst();

        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setUserId(updatedOrder.getUserId());
            order.setDeliveryAddress(updatedOrder.getDeliveryAddress());
            order.setContactPhone(updatedOrder.getContactPhone());
            order.setDeliveryMethod(updatedOrder.getDeliveryMethod());
            order.setStatus(updatedOrder.getStatus());
            order.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        boolean removed = orderList.removeIf(order -> order.getOrderId().equals(id));
        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
