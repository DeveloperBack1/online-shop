package com.schneider.onlineshop.controller;

import com.schneider.onlineshop.dto.OrderDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Orders", description = "Контроллер для работы с заказами",
        externalDocs = @ExternalDocumentation(
                description = "Ссылка на общую документацию по заказам",
                url = "https://example.com/docs/orders-controller"
        )
)
public interface OrderControllerInterface {
    @Operation(
            summary = "Все заказы",
            description = "Позволяет получить все заказы"
    )
    ResponseEntity<List<OrderDto>> getAllOrders();

    @Operation(
            summary = "Поиск по Id",
            description = "Позволяет найти информация по идентификатору Id нужный заказ"
    )

    ResponseEntity<OrderDto> getOrderById(Long id);

    @Hidden
    ResponseEntity<Void> deleteOrder(@PathVariable Long id);

}
