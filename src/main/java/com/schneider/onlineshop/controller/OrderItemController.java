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

      //  List<OrderItem> orderItems = new ArrayList<>();
        orderItemList.add(new OrderItem(1, 101, 1001, 2, 50.00)); // ID заказа 101, продукт 1001, 2 шт., цена 50.00
        orderItemList.add(new OrderItem(2, 101, 1002, 1, 30.00)); // ID заказа 101, продукт 1002, 1 шт., цена 30.00
        orderItemList.add(new OrderItem(3, 102, 1003, 5, 20.00)); // ID заказа 102, продукт 1003, 5 шт., цена 20.00
        orderItemList.add(new OrderItem(4, 103, 1004, 3, 70.00));


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

