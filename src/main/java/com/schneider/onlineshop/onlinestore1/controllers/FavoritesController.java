package com.schneider.onlineshop.onlinestore1.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favorites")
public class FavoritesController {
    @GetMapping
    public String getFavorites() {
        return "Привет, я контроллер - FavoritesController";
    }
}