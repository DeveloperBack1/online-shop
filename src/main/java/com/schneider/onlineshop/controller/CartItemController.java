package com.schneider.onlineshop.controller;

import com.schneider.onlineshop.model.CartItem;
import com.schneider.onlineshop.service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@RestController
//@RequestMapping("/cart-items")
//public class CartItemController {
//
//    List<CartItem> cartItemList = new ArrayList<>();
//
//
//    @GetMapping
//    public List<CartItem> getAllCartItems() {
//        CartItem item1 = new CartItem(1, 101, 1001, 2); // ID товара 1001, количество 2
//        CartItem item2 = new CartItem(2, 101, 1002, 1); // ID товара 1002, количество 1
//        CartItem item3 = new CartItem(3, 101, 1003, 5);
//        CartItem item4 = new CartItem(4, 101, 1004, 7);
//        CartItem item5 = new CartItem(5, 101, 1005, 6);
//        List<CartItem> cartItemList = new ArrayList<>();
//
//        cartItemList.add(item1);
//        cartItemList.add(item2);
//        cartItemList.add(item3);
//        cartItemList.add(item4);
//        cartItemList.add(item5);
//        return cartItemList;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CartItem> getCartItemById(@PathVariable int id) {
//        Optional<CartItem> cartItem = cartItemList.stream()
//                .filter(item -> item.getCartItemID() == id)
//                .findFirst();
//        return cartItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public ResponseEntity<CartItem> createCartItem(@RequestBody CartItem cartItem) {
//        cartItemList.add(cartItem);
//        return ResponseEntity.ok(cartItem);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<CartItem> updateCartItem(@PathVariable int id, @RequestBody CartItem updatedCartItem) {
//        Optional<CartItem> existingCartItem = cartItemList.stream()
//                .filter(item -> item.getCartItemID() == id)
//                .findFirst();
//
//        if (existingCartItem.isPresent()) {
//            CartItem cartItem = existingCartItem.get();
//            cartItem.setCartID(updatedCartItem.getCartID());
//            cartItem.setProductID(updatedCartItem.getProductID());
//            cartItem.setQuantity(updatedCartItem.getQuantity());
//            return ResponseEntity.ok(cartItem);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCartItem(@PathVariable int id) {
//        boolean removed = cartItemList.removeIf(item -> item.getCartItemID() == id);
//        if (removed) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        return ResponseEntity.ok(cartItemService.getAllCartItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable int id) {
        Optional<CartItem> cartItem = cartItemService.getCartItemById(id);
        return cartItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CartItem> createCartItem(@RequestBody CartItem cartItem) {
        CartItem createdCartItem = cartItemService.createCartItem(cartItem);
        return ResponseEntity.status(201).body(createdCartItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable int id, @RequestBody CartItem updatedCartItem) {
        Optional<CartItem> cartItem = cartItemService.updateCartItem(id, updatedCartItem);
        return cartItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable int id) {
        if (cartItemService.deleteCartItem(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
