package com.schneider.onlineshop.service;


import com.schneider.onlineshop.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private final List<OrderItem> orderItemList = new ArrayList<>();

    public List<OrderItem> getAllOrderItems() {
        return new ArrayList<>(orderItemList); // Возвращаем копию списка
    }

    public Optional<OrderItem> getOrderItemById(int id) {
        return orderItemList.stream()
                .filter(item -> item.getOrderItemID() == id)
                .findFirst();
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        orderItemList.add(orderItem);
        return orderItem;
    }

    public Optional<OrderItem> updateOrderItem(int id, OrderItem updatedOrderItem) {
        return getOrderItemById(id).map(existingOrderItem -> {
            existingOrderItem.setOrderID(updatedOrderItem.getOrderID());
            existingOrderItem.setProductID(updatedOrderItem.getProductID());
            existingOrderItem.setQuantity(updatedOrderItem.getQuantity());
            existingOrderItem.setPriceAtPurchase(updatedOrderItem.getPriceAtPurchase());
            return existingOrderItem;
        });
    }

    public boolean deleteOrderItem(int id) {
        return orderItemList.removeIf(item -> item.getOrderItemID() == id);
    }
}
