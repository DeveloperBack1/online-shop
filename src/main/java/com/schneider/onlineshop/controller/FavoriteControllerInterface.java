package com.schneider.onlineshop.controller;

import com.schneider.onlineshop.dto.FavoriteDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Favorites", description = "Контроллер для работы с фаворитами",
        externalDocs = @ExternalDocumentation(
                description = "Ссылка на общую документацию по  фаворитам",
                url = "https://example.com/docs/favorites-controller"
        )
)
public interface FavoriteControllerInterface {
    @Operation(
            summary = "Все фавориты",
            description = "Позволяет получить все фавориты"
    )
    ResponseEntity<List<FavoriteDto>> getAllFavorites();

    @Operation(
            summary = "Поиск по Id",
            description = "Позволяет найти информация по идентификатору Id нужный фаворит"
    )
    ResponseEntity<FavoriteDto> getFavoriteById(Long id);

    @Hidden
    ResponseEntity<Void> deleteFavorite(@PathVariable Long id);
}
