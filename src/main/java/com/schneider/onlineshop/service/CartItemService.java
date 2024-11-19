package com.schneider.onlineshop.service;


import com.schneider.onlineshop.model.CartItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    private final List<CartItem> cartItemList = new ArrayList<>();

    // Инициализация тестовыми данными
    public CartItemService() {
        cartItemList.add(new CartItem(1, 101, 1001, 2));
        cartItemList.add(new CartItem(2, 101, 1002, 1));
        cartItemList.add(new CartItem(3, 101, 1003, 5));
        cartItemList.add(new CartItem(4, 101, 1004, 7));
        cartItemList.add(new CartItem(5, 101, 1005, 6));
    }

    public List<CartItem> getAllCartItems() {
        return new ArrayList<>(cartItemList); // Возвращаем копию списка
    }

    public Optional<CartItem> getCartItemById(int id) {
        return cartItemList.stream()
                .filter(item -> item.getCartItemID() == id)
                .findFirst();
    }

    public CartItem createCartItem(CartItem cartItem) {
        cartItemList.add(cartItem);
        return cartItem;
    }

    public Optional<CartItem> updateCartItem(int id, CartItem updatedCartItem) {
        return getCartItemById(id).map(existingCartItem -> {
            existingCartItem.setCartID(updatedCartItem.getCartID());
            existingCartItem.setProductID(updatedCartItem.getProductID());
            existingCartItem.setQuantity(updatedCartItem.getQuantity());
            return existingCartItem;
        });
    }

    public boolean deleteCartItem(int id) {
        return cartItemList.removeIf(item -> item.getCartItemID() == id);
    }
}
