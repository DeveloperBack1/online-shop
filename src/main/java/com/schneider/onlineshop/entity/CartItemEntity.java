package com.schneider.onlineshop.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "CartItems")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartItemID")
    private int cartItemID;

    @ManyToOne
    @JoinColumn(name = "CartID")
    private CartEntity cart;

    @Column(name = "ProductID")
    private int productID;

    @Column(name = "Quantity")
    private int quantity;

}
