package com.schneider.onlineshop.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

// Entity для избранных товаров
@Entity
@Table(name = "Favorites")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FavoriteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FavoriteID")
    private Long favoriteID;

    @Column(name = "UserID")
    private Long userID;

    @Column(name = "ProductID")
    private Long productID;


}
