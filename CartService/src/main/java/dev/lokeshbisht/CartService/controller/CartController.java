package dev.lokeshbisht.CartService.controller;

import dev.lokeshbisht.CartService.dto.cart.CartDto;
import dev.lokeshbisht.CartService.model.Cart;
import dev.lokeshbisht.CartService.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/v1")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    public Cart createCart(@RequestBody CartDto cartDto) {
        return cartService.createCart(cartDto);
    }
}
