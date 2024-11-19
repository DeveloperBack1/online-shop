package com.schneider.onlineshop.service;

import com.schneider.onlineshop.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        // Инициализация тестовыми данными
        products.add(new Product(1L, "Laptop1", "Gaming laptop1", 1000.0, 1L, "url1", 1000.0, null, null));
        products.add(new Product(2L, "Laptop2", "Gaming laptop2", 1100.0, 2L, "url2", 1000.0, null, null));
        products.add(new Product(3L, "Laptop3", "Gaming laptop3", 1200.0, 3L, "url3", 1000.0, null, null));
        products.add(new Product(4L, "Laptop4", "Gaming laptop4", 1300.0, 4L, "url4", 1000.0, null, null));
        products.add(new Product(5L, "Smartphone", "Latest smartphone", 800.0, 5L, "url5", 700.0, null, null));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getProductById(Long id) {
        return products.stream().filter(p -> p.getProductID().equals(id)).findFirst();
    }

    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        return getProductById(id).map(existingProduct -> {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setCategoryID(updatedProduct.getCategoryID());
            existingProduct.setImageURL(updatedProduct.getImageURL());
            existingProduct.setDiscountPrice(updatedProduct.getDiscountPrice());
            return existingProduct;
        });
    }

    public boolean deleteProduct(Long id) {
        return products.removeIf(p -> p.getProductID().equals(id));
    }
}

