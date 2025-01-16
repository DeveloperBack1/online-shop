package com.schneider.onlineshop.controller;

import com.schneider.onlineshop.dto.ProductDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Products", description = "Контроллер для работы с продуктами",
        externalDocs = @ExternalDocumentation(
                description = "Ссылка на общую документацию по  продуктам",
                url = "https://example.com/docs/products-controller"
        )
)
public interface ProductControllerInterface {
    @Operation(
            summary = "Все категории",
            description = "Позволяет получить все категории товаров"
    )
    List<ProductDto> getAllCategories();
}
