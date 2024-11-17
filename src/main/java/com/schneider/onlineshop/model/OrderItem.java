package com.schneider.onlineshop.model;


import java.util.Objects;

//Товары в заказе

public class OrderItem {
    private int orderItemID;
    private int orderID;
    private int productID;
    private int quantity;
    private double priceAtPurchase;

    public OrderItem(){

    }

    public OrderItem(int orderItemID, int orderID, int productID,
                     int quantity, double priceAtPurchase) {
        this.orderItemID = orderItemID;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

    public int getOrderItemID() {
        return orderItemID;
    }

    public void setOrderItemID(int orderItemID) {
        this.orderItemID = orderItemID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
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

    public double getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(double priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return orderItemID == orderItem.orderItemID && orderID == orderItem.orderID && productID == orderItem.productID && quantity == orderItem.quantity && Double.compare(priceAtPurchase, orderItem.priceAtPurchase) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderItemID, orderID, productID, quantity, priceAtPurchase);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemID=" + orderItemID +
                ", orderID=" + orderID +
                ", productID=" + productID +
                ", quantity=" + quantity +
                ", priceAtPurchase=" + priceAtPurchase +
                '}';
    }


}
