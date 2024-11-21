package com.schneider.onlineshop.repository;

import com.schneider.onlineshop.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
