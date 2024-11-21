package com.schneider.onlineshop.repository;

import com.schneider.onlineshop.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
}
