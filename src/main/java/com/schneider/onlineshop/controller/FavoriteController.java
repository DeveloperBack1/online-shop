package com.schneider.onlineshop.controller;

import com.schneider.onlineshop.model.Favorite;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final List<Favorite> favoriteList = new ArrayList<>();

    @GetMapping
    public List<Favorite> getAllFavorites() {
        favoriteList.add(new Favorite(1L, 101L, 1001L)); // Пользователь 101 добавил товар 1001 в избранное
        favoriteList.add(new Favorite(2L, 101L, 1002L)); // Пользователь 101 добавил товар 1002 в избранное
        favoriteList.add(new Favorite(3L, 102L, 1003L)); // Пользователь 102 добавил товар 1003 в избранное
        favoriteList.add(new Favorite(4L, 103L, 1004L)); // Пользователь 103 добавил товар 1004 в избранное

        return favoriteList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favorite> getFavoriteById(@PathVariable Long id) {
        return favoriteList.stream()
                .filter(favorite -> favorite.getFavoriteID().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Favorite> addFavorite(@RequestBody Favorite favorite) {
        if (favoriteList.stream().anyMatch(f -> f.getFavoriteID().equals(favorite.getFavoriteID()))) {
            return ResponseEntity.badRequest().body(null);
        }
        favoriteList.add(favorite);
        return ResponseEntity.ok(favorite);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long id) {
        boolean removed = favoriteList.removeIf(favorite -> favorite.getFavoriteID().equals(id));
        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Favorite> updateFavorite(@PathVariable Long id, @RequestBody Favorite updatedFavorite) {
        for (int i = 0; i < favoriteList.size(); i++) {
            if (favoriteList.get(i).getFavoriteID().equals(id)) {
                favoriteList.set(i, updatedFavorite);
                return ResponseEntity.ok(updatedFavorite);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
