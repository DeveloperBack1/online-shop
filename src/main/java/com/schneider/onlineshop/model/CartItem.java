package com.schneider.onlineshop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Товары в корзине

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

//    public static void main(String[] args) {
//        CartItem item1 = new CartItem(1, 101, 1001, 2); // ID товара 1001, количество 2
//        CartItem item2 = new CartItem(2, 101, 1002, 1); // ID товара 1002, количество 1
//        CartItem item3 = new CartItem(3, 101, 1003, 5);
//        CartItem item4 = new CartItem(4, 101, 1004, 7);
//        CartItem item5 = new CartItem(5, 101, 1005, 6);
//        List<CartItem> cartItemList = new ArrayList<>();
//        cartItemList.add(item1);
//        cartItemList.add(item2);
//        cartItemList.add(item3);
//        cartItemList.add(item4);
//        cartItemList.add(item5);
//    }
}
