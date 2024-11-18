package com.schneider.onlineshop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Корзина

public class Cart {
    private int cartID;
    private int userID;
    private List<CartItem> cartItems = new ArrayList<>();

    public Cart() {

    }

    public Cart(int cartID, int userID, List<CartItem> cartItems) {

        this.cartID = cartID;
        this.userID = userID;
        this.cartItems = cartItems;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartID=" + cartID +
                ", userID=" + userID +
                ", cartItems=" + cartItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return cartID == cart.cartID && userID == cart.userID && Objects.equals(cartItems, cart.cartItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartID, userID, cartItems);
    }
}

