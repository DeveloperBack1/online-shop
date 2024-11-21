package com.schneider.onlineshop.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Orders")
   @AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Генерация первичного ключа
    @Column(name = "OrderID")

    private Long orderId;

    @Column(name = "UserID")
    private Long userId;

    @Column(name = "CreatedAt")
    private Timestamp createdAt;

    @Column(name = "UpdatedAt")
    private Timestamp updatedAt;

    @Column(name = "DeliveryAddress")
    private String deliveryAddress;

    @Column(name = "ContactPhone")
    private String contactPhone;

    @Column(name = "DeliveryMethod")
    private String deliveryMethod;

}
