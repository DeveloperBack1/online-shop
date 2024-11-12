package com.schneider.onlineshop.onlinestore1.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @GetMapping
    public String getOrders() {
        return "Привет, я контроллер - OrdersController";
    }
}
