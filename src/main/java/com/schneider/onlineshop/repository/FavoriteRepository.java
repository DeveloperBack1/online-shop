package com.schneider.onlineshop.repository;

import com.schneider.onlineshop.entity.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity,Long> {
}
