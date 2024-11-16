package com.schneider.onlineshop.controller;

import com.schneider.onlineshop.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final List<Product> products = new ArrayList<>();

    // Инициализация с тестовыми данными
    public ProductController() {
        products.add(new Product(1L, "Product1", "Description1", 100.0, 6L, "image1.jpg", 90.0, new Timestamp(System.currentTimeMillis()), null));
        products.add(new Product(2L, "Product2", "Description2", 200.0, 7L, "image2.jpg", null, new Timestamp(System.currentTimeMillis()), null));
        products.add(new Product(3L, "Product3", "Description3", 300.0, 8L, "image3.jpg", null, new Timestamp(System.currentTimeMillis()), null));
        products.add(new Product(4L, "Product4", "Description4", 400.0, 9L, "image4.jpg", null, new Timestamp(System.currentTimeMillis()), null));
        products.add(new Product(5L, "Product5", "Description5", 500.0, 10L, "image5.jpg", null, new Timestamp(System.currentTimeMillis()), null));
    }

    // Получить все продукты
    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    // Получить продукт по ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = products.stream()
                .filter(p -> p.getProductID().equals(id))
                .findFirst();
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Создать новый продукт
    @PostMapping

    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        product.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        product.setUpdatedAt(null);
        products.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // Обновить существующий продукт
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Optional<Product> existingProduct = products.stream()
                .filter(p -> p.getProductID().equals(id))
                .findFirst();

        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setCategoryID(updatedProduct.getCategoryID());
            product.setImageURL(updatedProduct.getImageURL());
            product.setDiscountPrice(updatedProduct.getDiscountPrice());
            product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Удалить продукт
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean removed = products.removeIf(product -> product.getProductID().equals(id));
        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
