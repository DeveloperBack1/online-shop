package com.schneider.onlineshop.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;

//Товары
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder

@Schema(description="Suschnost Produktov")
public class ProductDto {
    @Schema(description = "Уникальный идентификатор produktov",
            example = "1",accessMode = Schema.AccessMode.READ_ONLY)
    private Long productId;
    @Schema(description ="ProductNaming", example = "Neue Product")
    private String name;
    private String description;
    private Double price;
    private String imageURL;
    private Double discountPrice;
    Timestamp createdAt;
    Timestamp updatedAt;

}

