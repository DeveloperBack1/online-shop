package onlinestore.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @GetMapping
    public String getCart() {
        return "Привет, я контроллер - CartController";
    }
}