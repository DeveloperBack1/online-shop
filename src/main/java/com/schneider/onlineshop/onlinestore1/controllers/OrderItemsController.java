package com.schneider.onlineshop.onlinestore1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderItems")
public class OrderItemsController {
    @GetMapping
    public String getOrderItems() {
        return "Привет, я контроллер - OrderItemsController";
    }
}