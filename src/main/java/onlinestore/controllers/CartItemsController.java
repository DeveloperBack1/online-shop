package onlinestore.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartItems")
public class CartItemsController {
    @GetMapping
    public String getCartItems() {
        return "Привет, я контроллер - CartItemsController";
    }
}