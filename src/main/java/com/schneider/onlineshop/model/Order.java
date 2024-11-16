package com.schneider.onlineshop.model;

import java.sql.Timestamp;
import java.util.Objects;

public class Order {
    private Long orderId;
    private Long userId;
    Timestamp createdAt;
    private String deliveryAddress;
    private String contactPhone;
    private String deliveryMethod;
    Enum Status;
    Timestamp updatedAt;

    public Order(Long orderId, Long userId, Timestamp createdAt, String deliveryAddress, String contactPhone,
                 String deliveryMethod, Enum status, Timestamp updatedAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.deliveryAddress = deliveryAddress;
        this.contactPhone = contactPhone;
        this.deliveryMethod = deliveryMethod;
        Status = status;
        this.updatedAt = updatedAt;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public Enum getStatus() {
        return Status;
    }

    public void setStatus(Enum status) {
        Status = status;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) && Objects.equals(userId, order.userId) && Objects.equals(createdAt, order.createdAt) && Objects.equals(deliveryAddress, order.deliveryAddress) && Objects.equals(contactPhone, order.contactPhone) && Objects.equals(deliveryMethod, order.deliveryMethod) && Objects.equals(Status, order.Status) && Objects.equals(updatedAt, order.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, createdAt, deliveryAddress, contactPhone, deliveryMethod, Status, updatedAt);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", createdAt=" + createdAt +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", deliveryMethod='" + deliveryMethod + '\'' +
                ", Status=" + Status +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
