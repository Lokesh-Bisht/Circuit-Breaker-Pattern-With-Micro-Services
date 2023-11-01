package dev.lokeshbisht.CartService.service.impl;

import dev.lokeshbisht.CartService.dto.cart.CartDto;
import dev.lokeshbisht.CartService.exceptions.CartNotFoundException;
import dev.lokeshbisht.CartService.mapper.CartMapper;
import dev.lokeshbisht.CartService.model.Cart;
import dev.lokeshbisht.CartService.repository.CartRepository;
import dev.lokeshbisht.CartService.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartMapper cartMapper;

    public static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Override
    public Cart createCart(CartDto cartDto) {
        logger.info("Create new cart: {}", cartDto);
        Cart cart = cartMapper.toCart(cartDto);
        cart.setCreatedAt(new Date());
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartByCartId(String cartId) {
        logger.info("Get cart: {}", cartId);
        Optional<Cart> cart = cartRepository.findById(cartId);
        if (cart.isEmpty()) {
            throw new CartNotFoundException("Cart not found.");
        }
        return cart.get();
    }
}
