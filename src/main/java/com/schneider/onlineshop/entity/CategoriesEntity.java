package com.schneider.onlineshop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CategoriesEntity {
    @Id
    @Column(name = "CategoryID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryID;
    @Column(name = "Name")
    private String name;


}
