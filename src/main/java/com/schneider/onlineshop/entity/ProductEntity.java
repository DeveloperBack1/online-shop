package com.schneider.onlineshop.entity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;

// Entity для товаров
@Entity
@Table(name = "Products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private Long productID;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price")
    private Double price;

    @Column(name = "CategoryID")
    private Long categoryID;

    @Column(name = "ImageURL")
    private String imageURL;

    @Column(name = "DiscountPrice")
    private Double discountPrice;

    @Column(name = "CreatedAt")
    private Timestamp createdAt;

    @Column(name = "UpdatedAt")
    private Timestamp updatedAt;


}
