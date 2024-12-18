package com.schneider.onlineshop.service;

import com.schneider.onlineshop.dto.ProductDto;
import com.schneider.onlineshop.entity.CategoriesEntity;
import com.schneider.onlineshop.entity.ProductEntity;
import com.schneider.onlineshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productsRepositoryMock;

    @InjectMocks
    private ProductService productService;

    private ProductEntity productEntity;
    private CategoriesEntity categoriesEntity;



    @Test
    public void testGetAllProducts() {
        // Создаём тестовые данные
        ProductEntity product1 = new ProductEntity(1L, "Product 1",
                "description",20.5,"123",10.0,
                Timestamp.valueOf(LocalDateTime.now())
                ,null, categoriesEntity);
        ProductEntity product2 = new ProductEntity(2L, "Product 2",
                "description1",22.5,"103",10.0,
                Timestamp.valueOf(LocalDateTime.now())
                ,null, categoriesEntity);

        List<ProductEntity> productEntities = Arrays.asList(product1, product2);


        when(productsRepositoryMock.findAll()).thenReturn(productEntities);

        // Вызов метода
        List<ProductDto> result = productService.getAllProducts();

        // Проверки
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getProductId());
        assertEquals("Product 1", result.get(0).getName());
        assertEquals(2L, result.get(1).getProductId());
        assertEquals("Product 2", result.get(1).getName());


        verify(productsRepositoryMock, times(1)).findAll();
    }

}