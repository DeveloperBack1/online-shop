package com.schneider.onlineshop.repository;

import com.schneider.onlineshop.entity.CategoriesEntity;
import com.schneider.onlineshop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT ue FROM UserEntity ue WHERE ue.name=?1")
    CategoriesEntity findByName(String name);

    // Чистый SQL
    @Query(value = "SELECT * FROM Users ue WHERE ue.Name=?1 AND Id=?2", nativeQuery = true)
    CategoriesEntity findByNameNative(String name);

    Optional<UserEntity> findByEmail(String email);


}
