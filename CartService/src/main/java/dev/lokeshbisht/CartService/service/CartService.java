package dev.lokeshbisht.CartService.service;

import dev.lokeshbisht.CartService.dto.cart.CartDto;
import dev.lokeshbisht.CartService.model.Cart;

public interface CartService {

    Cart createCart(CartDto cartDto);
}
