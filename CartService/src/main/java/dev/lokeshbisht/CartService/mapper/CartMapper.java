package dev.lokeshbisht.CartService.mapper;

import dev.lokeshbisht.CartService.dto.cart.CartDto;
import dev.lokeshbisht.CartService.model.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

    Cart toCart(CartDto cartDto);
    CartDto toCartDto(Cart cart);
}
