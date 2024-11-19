package com.schneider.onlineshop.service;

import com.schneider.onlineshop.model.Favorite;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    private final List<Favorite> favoriteList = new ArrayList<>();

    public List<Favorite> getAllFavorites() {
        return new ArrayList<>(favoriteList); // Возвращаем копию списка
    }

    public Optional<Favorite> getFavoriteById(Long id) {
        return favoriteList.stream()
                .filter(favorite -> favorite.getFavoriteID().equals(id))
                .findFirst();
    }

    public Favorite addFavorite(Favorite favorite) {
        favoriteList.add(favorite);
        return favorite;
    }

    public Optional<Favorite> updateFavorite(Long id, Favorite updatedFavorite) {
        for (int i = 0; i < favoriteList.size(); i++) {
            if (favoriteList.get(i).getFavoriteID().equals(id)) {
                favoriteList.set(i, updatedFavorite);
                return Optional.of(updatedFavorite);
            }
        }
        return Optional.empty();
    }

    public boolean deleteFavorite(Long id) {
        return favoriteList.removeIf(favorite -> favorite.getFavoriteID().equals(id));
    }

    public boolean isDuplicate(Long id) {
        return favoriteList.stream().anyMatch(f -> f.getFavoriteID().equals(id));
    }
}
