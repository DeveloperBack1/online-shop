package com.schneider.onlineshop.controller;

import com.schneider.onlineshop.model.CartItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    private final List<CartItem> cartItemList = new ArrayList<>();

    @GetMapping
    public List<CartItem> getAllCartItems() {
        return cartItemList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable int id) {
        Optional<CartItem> cartItem = cartItemList.stream()
                .filter(item -> item.getCartItemID() == id)
                .findFirst();
        return cartItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CartItem> createCartItem(@RequestBody CartItem cartItem) {
        cartItemList.add(cartItem);
        return ResponseEntity.ok(cartItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable int id, @RequestBody CartItem updatedCartItem) {
        Optional<CartItem> existingCartItem = cartItemList.stream()
                .filter(item -> item.getCartItemID() == id)
                .findFirst();

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setCartID(updatedCartItem.getCartID());
            cartItem.setProductID(updatedCartItem.getProductID());
            cartItem.setQuantity(updatedCartItem.getQuantity());
            return ResponseEntity.ok(cartItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable int id) {
        boolean removed = cartItemList.removeIf(item -> item.getCartItemID() == id);
        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
