package com.schneider.onlineshop.controller;

import com.schneider.onlineshop.model.Product;
import com.schneider.onlineshop.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    // Инъекция сервиса через конструктор
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Получить все продукты
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    // Получить продукт по ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
       return productService.getProductById(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
               .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Создать новый продукт
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product createdProduct = productService.addProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // Обновить продукт
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct)
                .map(product -> new ResponseEntity<>(product, HttpStatus.ACCEPTED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Удалить продукт
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.deleteProduct(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
