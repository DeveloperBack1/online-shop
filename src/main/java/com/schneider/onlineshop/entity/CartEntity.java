package com.schneider.onlineshop.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Cart")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartID")
    private int cartID;

    @Column(name = "UserID")
    private int userID;
//
//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<CartItemEntity> cartItems = new ArrayList<>();
//
//
//    public void addCartItem(CartItemEntity cartItem) {
//        cartItems.add(cartItem);
//        cartItem.setCart(this);
//    }
//
//    public void removeCartItem(CartItemEntity cartItem) {
//        cartItems.remove(cartItem);
//        cartItem.setCart(null);
//    }

}
