package com.schneider.onlineshop.repository;

import com.schneider.onlineshop.entity.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<CategoriesEntity, Long> {

}
