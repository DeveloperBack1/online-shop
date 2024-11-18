package com.schneider.onlineshop.model;

import java.util.Objects;

//Избранные товары

public class Favorite {
    private Long favoriteID;
    private Long userID;
    private Long productID;

    public Favorite(Long favoriteID, Long userID, Long productID) {

        this.favoriteID = favoriteID;
        this.userID = userID;
        this.productID = productID;
    }

    public Long getFavoriteID() {
        return favoriteID;
    }

    public void setFavoriteID(Long favoriteID) {
        this.favoriteID = favoriteID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorite = (Favorite) o;
        return Objects.equals(favoriteID, favorite.favoriteID) && Objects.equals(userID, favorite.userID) && Objects.equals(productID, favorite.productID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(favoriteID, userID, productID);
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "favoriteID=" + favoriteID +
                ", userID=" + userID +
                ", productID=" + productID +
                '}';
    }
}

