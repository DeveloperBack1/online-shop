package com.schneider.onlineshop.controller;

import com.schneider.onlineshop.model.Favorite;
import com.schneider.onlineshop.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping
    public ResponseEntity<List<Favorite>> getAllFavorites() {
        return ResponseEntity.ok(favoriteService.getAllFavorites());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favorite> getFavoriteById(@PathVariable Long id) {
        Optional<Favorite> favorite = favoriteService.getFavoriteById(id);
        return favorite.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Favorite> addFavorite(@RequestBody Favorite favorite) {
        if (favoriteService.isDuplicate(favorite.getFavoriteID())) {
            return ResponseEntity.badRequest().build();
        }
        Favorite createdFavorite = favoriteService.addFavorite(favorite);
        return ResponseEntity.status(201).body(createdFavorite);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Favorite> updateFavorite(@PathVariable Long id, @RequestBody Favorite updatedFavorite) {
        Optional<Favorite> updated = favoriteService.updateFavorite(id, updatedFavorite);
        return updated.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long id) {
        if (favoriteService.deleteFavorite(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
