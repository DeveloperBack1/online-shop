package com.schneider.onlineshop.controller;

import com.schneider.onlineshop.model.Cart;
import com.schneider.onlineshop.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        return new ResponseEntity<>(cartService.getAllCarts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable int id) {
        return cartService.getCartById(id)
                .map(cart -> new ResponseEntity<>(cart, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        return new ResponseEntity<>(cartService.addCart(cart), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable int id, @RequestBody Cart updatedCart) {
        return cartService.updateCart(id, updatedCart)
                .map(cart -> new ResponseEntity<>(cart, HttpStatus.ACCEPTED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable int id) {
        if (cartService.deleteCart(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
