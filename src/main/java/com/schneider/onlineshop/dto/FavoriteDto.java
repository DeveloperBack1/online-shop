package com.schneider.onlineshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

//Избранные товары
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description="Suschnost Favoritov")
public class FavoriteDto {

    @Schema(description = "Уникальный идентификатор favoritov",
            example = "1",accessMode = Schema.AccessMode.READ_ONLY)
    private Long favoriteID;
    @Schema(description = "Уникальный идентификатор polzovatelej",
            example = "1")
    private Long userID;
    @Schema(description = "Уникальный идентификатор produktov",
            example = "1")
    private Long productID;

    private UserDto user;

//    public FavoriteDto(Long favoriteID, Long userID, Long productID) {
//
//        this.favoriteID = favoriteID;
//        this.userID = userID;
//        this.productID = productID;
//    }

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
        FavoriteDto favoriteDTO = (FavoriteDto) o;
        return Objects.equals(favoriteID, favoriteDTO.favoriteID) && Objects.equals(userID, favoriteDTO.userID) && Objects.equals(productID, favoriteDTO.productID);
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

