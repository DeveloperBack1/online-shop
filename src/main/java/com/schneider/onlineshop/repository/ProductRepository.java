package com.schneider.onlineshop.repository;

import com.schneider.onlineshop.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
