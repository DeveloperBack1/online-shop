package com.schneider.onlineshop.repository;

import com.schneider.onlineshop.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
}
