package dev.lokeshbisht.CartService.controller;

import dev.lokeshbisht.CartService.constants.MessageTemplates;
import dev.lokeshbisht.CartService.dto.ApiResponseDto;
import dev.lokeshbisht.CartService.dto.MetaDataDto;
import dev.lokeshbisht.CartService.dto.cart.CartDto;
import dev.lokeshbisht.CartService.dto.cart.CartInfoDto;
import dev.lokeshbisht.CartService.model.Cart;
import dev.lokeshbisht.CartService.service.CartService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/v1")
public class CartController {

    @Autowired
    private CartService cartService;

    public static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @PostMapping("/cart")
    public Cart createCart(@RequestBody CartDto cartDto) {
        return cartService.createCart(cartDto);
    }

    @GetMapping("/cart/{cartId}/details")
    @CircuitBreaker(name = "catalogServiceBreaker", fallbackMethod = "catalogFallback")
    public ApiResponseDto<CartInfoDto> getCart(@PathVariable String cartId) {
        return cartService.getCartDetails(cartId);
    }

    public ApiResponseDto<CartInfoDto> catalogFallback(String cartId, Exception ex) {
        logger.error("Executing catalog fallback because catalog service is down: {}", ex.getMessage());
        MetaDataDto metaDataDto = MetaDataDto.builder().build();
        return new ApiResponseDto<CartInfoDto>(null, "OK",  new String []{MessageTemplates.CATALOG_SERVICE_DOWN}, metaDataDto);
    }
}
