package com.schneider.onlineshop.onlinestore1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    @GetMapping
    public String getCategories() {
        return "Привет, я контроллер - CategoriesController";
    }
}