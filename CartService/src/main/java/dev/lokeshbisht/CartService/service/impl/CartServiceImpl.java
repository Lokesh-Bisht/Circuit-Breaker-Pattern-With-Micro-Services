package dev.lokeshbisht.CartService.service.impl;

import dev.lokeshbisht.CartService.dto.ApiResponseDto;
import dev.lokeshbisht.CartService.dto.MetaDataDto;
import dev.lokeshbisht.CartService.dto.cart.CartDto;
import dev.lokeshbisht.CartService.dto.catalog.Product;
import dev.lokeshbisht.CartService.exceptions.CartNotFoundException;
import dev.lokeshbisht.CartService.mapper.CartMapper;
import dev.lokeshbisht.CartService.model.Cart;
import dev.lokeshbisht.CartService.repository.CartRepository;
import dev.lokeshbisht.CartService.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private RestTemplate restTemplate;

    public static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Override
    public Cart createCart(CartDto cartDto) {
        logger.info("Create new cart: {}", cartDto);
        Cart cart = cartMapper.toCart(cartDto);
        cart.setCreatedAt(new Date());
        return cartRepository.save(cart);
    }

    @Override
    public ApiResponseDto<Cart> getCartByCartId(String cartId) {
        logger.info("Get cart: {}", cartId);
        double startTime = System.currentTimeMillis();
        Optional<Cart> cart = cartRepository.findById(cartId);
        if (cart.isEmpty()) {
            throw new CartNotFoundException("Cart not found.");
        }
        ResponseEntity<List<Product>> responseEntity = restTemplate.exchange("https://api.sampleapis.com/coffee/hot", HttpMethod.GET, new HttpEntity<>(""), new ParameterizedTypeReference<>(){});
        MetaDataDto metaDataDto = MetaDataDto.builder()
            .page(1)
            .size(1)
            .total(1)
            .took(System.currentTimeMillis() - startTime)
            .build();
        return new ApiResponseDto<>(cart.get(), "OK", null, metaDataDto);
    }
}
