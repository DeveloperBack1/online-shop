package com.schneider.onlineshop.model;

import java.util.Objects;

public class CartItem {
    private int cartItemID;
    private int cartID;
    private int productID;
    private int quantity;

    public  CartItem(){

    }

    public CartItem(int cartItemID, int cartID, int productID, int quantity) {
        this.cartItemID = cartItemID;
        this.cartID = cartID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public int getCartItemID() {
        return cartItemID;
    }

    public void setCartItemID(int cartItemID) {
        this.cartItemID = cartItemID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return cartItemID == cartItem.cartItemID && cartID == cartItem.cartID && productID == cartItem.productID && quantity == cartItem.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartItemID, cartID, productID, quantity);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemID=" + cartItemID +
                ", cartID=" + cartID +
                ", productID=" + productID +
                ", quantity=" + quantity +
                '}';
    }
}
