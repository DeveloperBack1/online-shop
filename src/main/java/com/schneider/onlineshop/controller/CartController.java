package com.schneider.onlineshop.controller;


import com.schneider.onlineshop.model.Cart;
import com.schneider.onlineshop.model.CartItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final List<Cart> cartList = new ArrayList<>();

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable int id) {
        Optional<Cart> cart = cartList.stream()
                .filter(c -> c.getCartID() == id)
                .findFirst();
        return cart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        cartList.add(cart);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable int id, @RequestBody Cart updatedCart) {
        Optional<Cart> existingCart = cartList.stream()
                .filter(c -> c.getCartID() == id)
                .findFirst();

        if (existingCart.isPresent()) {
            Cart cart = existingCart.get();
            cart.setUserID(updatedCart.getUserID());
            cart.setCartItems(updatedCart.getCartItems());
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable int id) {
        boolean removed = cartList.removeIf(cart -> cart.getCartID() == id);
        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<Cart> addCartItem(@PathVariable int cartId, @RequestBody CartItem cartItem) {
        Optional<Cart> existingCart = cartList.stream()
                .filter(c -> c.getCartID() == cartId)
                .findFirst();

        if (existingCart.isPresent()) {
            Cart cart = existingCart.get();
            cart.getCartItems().add(cartItem);
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.notFound().build();

        }
    }
}
