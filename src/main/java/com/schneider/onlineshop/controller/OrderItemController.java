package com.schneider.onlineshop.controller;

import com.schneider.onlineshop.model.OrderItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {

    private final List<OrderItem> orderItemList = new ArrayList<>();

    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable int id) {
        Optional<OrderItem> orderItem = orderItemList.stream()
                .filter(item -> item.getOrderItemID() == id)
                .findFirst();
        return orderItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        orderItemList.add(orderItem);
        return ResponseEntity.ok(orderItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable int id, @RequestBody OrderItem updatedOrderItem) {
        Optional<OrderItem> existingOrderItem = orderItemList.stream()
                .filter(item -> item.getOrderItemID() == id)
                .findFirst();

        if (existingOrderItem.isPresent()) {
            OrderItem orderItem = existingOrderItem.get();
            orderItem.setOrderID(updatedOrderItem.getOrderID());
            orderItem.setProductID(updatedOrderItem.getProductID());
            orderItem.setQuantity(updatedOrderItem.getQuantity());
            orderItem.setPriceAtPurchase(updatedOrderItem.getPriceAtPurchase());
            return ResponseEntity.ok(orderItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable int id) {
        boolean removed = orderItemList.removeIf(item -> item.getOrderItemID() == id);
        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

