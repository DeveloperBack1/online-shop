package com.schneider.onlineshop.service;

import com.schneider.onlineshop.dto.ProductDto;
import com.schneider.onlineshop.entity.ProductEntity;
import com.schneider.onlineshop.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productsRepository;

    @PostConstruct
    void init() {
        ProductEntity product1 = new ProductEntity(null, "Laptop1", "Gaming laptop1",
                1000.0, 1L, "url1", 1000.0, null, null);
        productsRepository.save(product1);
        ProductEntity product2 = new ProductEntity(null, "Laptop2", "Gaming laptop2",
                1100.0, 2L, "url2", 1000.0, null, null);
        productsRepository.save(product2);
        ProductEntity product3 = new ProductEntity(null, "Laptop3", "Gaming laptop3",
                1200.0, 3L, "url3", 1000.0, null, null);
        productsRepository.save(product3);

        System.out.println("Выполняем логику при создании объекта " + this.getClass().getName());
    }

    public List<ProductDto> getAllProducts() {
        List<ProductEntity> productsEntities = productsRepository.findAll();
        return productsEntities.stream()
                .map(entity -> new ProductDto(entity.getProductID(), entity.getName(), entity.getDescription(), entity.getPrice(), entity.getCategoryID(),
                        entity.getImageURL(), entity.getDiscountPrice(), entity.getCreatedAt(), entity.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {
        ProductEntity productsEntity = productsRepository.findById(id).orElse(new ProductEntity());
        return new ProductDto(productsEntity.getProductID(), productsEntity.getName(), productsEntity.getDescription(),
                productsEntity.getPrice(), productsEntity.getCategoryID(), productsEntity.getImageURL(),
                productsEntity.getDiscountPrice(),productsEntity.getCreatedAt(), productsEntity.getUpdatedAt() );
    }

    public boolean createProduct(ProductDto newProduct) {
        ProductEntity createProductEntity = new ProductEntity(null, newProduct.getName(), newProduct.getDescription(),
                newProduct.getPrice(), newProduct.getCategoryID(), newProduct.getImageURL(), newProduct.getDiscountPrice(),
                newProduct.getCreatedAt(), newProduct.getUpdatedAt());
        ProductEntity returnProductEntity = productsRepository.save(createProductEntity);
        return returnProductEntity.getProductID() != null;
    }

//    public ProductDto updateProduct(ProductDto updProduct) {
//        ProductEntity updateProductEntity = new ProductEntity(updProduct.getProductID(), updProduct.getName(),
//                updProduct.getDescription(), updProduct.getPrice(), updProduct.getCategoryID(), updProduct.getImageURL(),
//                updProduct.getDiscountPrice(), updProduct.getCreatedAt(), updProduct.getUpdatedAt());
//        ProductEntity returnProductEntity = productsRepository.save(updateProductEntity);
//        return new ProductDto(returnProductEntity.getProductID(), returnProductEntity.getName(),
//                returnProductEntity.getDescription(), returnProductEntity.getPrice(), updProduct.getCategoryID(),
//                updateProductEntity.getImageURL(), updateProductEntity.getDiscountPrice(),
//                updateProductEntity.getCreatedAt(),updateProductEntity.getUpdatedAt());
//    }

    public Optional<ProductDto> updateProduct(Long id, ProductDto updProduct) {
        // Проверяем существование продукта
        Optional<ProductEntity> existingProduct = productsRepository.findById(id);

        if (existingProduct.isEmpty()) {
            return Optional.empty(); // Если продукта нет, возвращаем пустой Optional
        }

        // Обновляем продукт
        ProductEntity updatedEntity = existingProduct.get();
        updatedEntity.setName(updProduct.getName());
        updatedEntity.setDescription(updProduct.getDescription());
        updatedEntity.setPrice(updProduct.getPrice());
        updatedEntity.setCategoryID(updProduct.getCategoryID());
        updatedEntity.setImageURL(updProduct.getImageURL());
        updatedEntity.setDiscountPrice(updProduct.getDiscountPrice());
        updatedEntity.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        // Сохраняем изменения
        ProductEntity savedEntity = productsRepository.save(updatedEntity);

        // Возвращаем обновленный DTO
        return Optional.of(new ProductDto(
                savedEntity.getProductID(),
                savedEntity.getName(),
                savedEntity.getDescription(),
                savedEntity.getPrice(),
                savedEntity.getCategoryID(),
                savedEntity.getImageURL(),
                savedEntity.getDiscountPrice(),
                savedEntity.getCreatedAt(),
                savedEntity.getUpdatedAt()
        ));
    }



    public ProductDto addProduct(ProductDto productDto) {
        // Создаем сущность из DTO
        ProductEntity newProductEntity = new ProductEntity(null, productDto.getName(), productDto.getDescription(),
                productDto.getPrice(), productDto.getCategoryID(), productDto.getImageURL(),
                productDto.getDiscountPrice(), null, null);

        // Сохраняем продукт в репозитории
        ProductEntity savedProduct = productsRepository.save(newProductEntity);

        // Возвращаем DTO из сохраненной сущности
        return new ProductDto(savedProduct.getProductID(), savedProduct.getName(), savedProduct.getDescription(),
                savedProduct.getPrice(), savedProduct.getCategoryID(), savedProduct.getImageURL(),
                savedProduct.getDiscountPrice(), savedProduct.getCreatedAt(), savedProduct.getUpdatedAt());
    }

    public boolean deleteProduct(Long id) {
        productsRepository.deleteById(id);
        return false;
    }
}

