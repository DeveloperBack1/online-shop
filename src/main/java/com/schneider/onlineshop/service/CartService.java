package com.schneider.onlineshop.service;

import com.schneider.onlineshop.model.Cart;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final List<Cart> carts = new ArrayList<>();

    public CartService() {
        // Тестовые данные
        carts.add(new Cart(1, 101, new ArrayList<>()));
        carts.add(new Cart(2, 102, new ArrayList<>()));
        carts.add(new Cart(3, 103, new ArrayList<>()));
        carts.add(new Cart(4, 104, new ArrayList<>()));
        carts.add(new Cart(5, 105, new ArrayList<>()));
    }

    public List<Cart> getAllCarts() {
        return carts;
    }

    public Optional<Cart> getCartById(int id) {
        return carts.stream().filter(c -> c.getCartID() == id).findFirst();
    }

    public Cart addCart(Cart cart) {
        carts.add(cart);
        return cart;
    }

    public Optional<Cart> updateCart(int id, Cart updatedCart) {
        return getCartById(id).map(existingCart -> {
            existingCart.setUserID(updatedCart.getUserID());
            existingCart.setCartItems(updatedCart.getCartItems());
            return existingCart;
        });
    }

    public boolean deleteCart(int id) {
        return carts.removeIf(c -> c.getCartID() == id);
    }
}
