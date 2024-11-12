package com.schneider.onlineshop.onlinestore1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @GetMapping
    public String getProducts() {
        return "Привет, я контроллер - ProductsController";
    }
}