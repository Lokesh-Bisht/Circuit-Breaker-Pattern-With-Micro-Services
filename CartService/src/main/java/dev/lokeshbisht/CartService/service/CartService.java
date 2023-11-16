package dev.lokeshbisht.CartService.service;

import dev.lokeshbisht.CartService.dto.ApiResponseDto;
import dev.lokeshbisht.CartService.dto.cart.CartDto;
import dev.lokeshbisht.CartService.dto.cart.CartInfoDto;
import dev.lokeshbisht.CartService.model.Cart;

public interface CartService {

    Cart createCart(CartDto cartDto);
    ApiResponseDto<CartInfoDto> getCartDetails(String cartId);
    Cart getCart(String cartId);
}
