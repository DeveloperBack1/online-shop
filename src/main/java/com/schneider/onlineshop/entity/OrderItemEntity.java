package com.schneider.onlineshop.entity;
import jakarta.persistence.*;
import lombok.*;


// Entity for Order Items
@Entity
@Table(name = "OrderItems")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderItemID")
    private int orderItemID;

    @Column(name = "OrderID")
    private int orderID;

    @Column(name = "ProductID")
    private int productID;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "PriceAtPurchase")
    private double priceAtPurchase;

}
