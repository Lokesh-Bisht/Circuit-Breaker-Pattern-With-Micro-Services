package dev.lokeshbisht.CartService.service.impl;

import dev.lokeshbisht.CartService.dto.ApiResponseDto;
import dev.lokeshbisht.CartService.dto.MetaDataDto;
import dev.lokeshbisht.CartService.dto.cart.CartDto;
import dev.lokeshbisht.CartService.dto.cart.CartInfoDto;
import dev.lokeshbisht.CartService.dto.cart.ItemsDto;
import dev.lokeshbisht.CartService.dto.catalog.Product;
import dev.lokeshbisht.CartService.dto.catalog.ProductSearchRequestDto;
import dev.lokeshbisht.CartService.exceptions.CartNotFoundException;
import dev.lokeshbisht.CartService.mapper.CartMapper;
import dev.lokeshbisht.CartService.model.Cart;
import dev.lokeshbisht.CartService.repository.CartRepository;
import dev.lokeshbisht.CartService.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private RestTemplate restTemplate;

    public static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    public static final String catalogServiceBaseUrl = "http://localhost:8080/catalog_service";

    @Override
    public Cart createCart(CartDto cartDto) {
        logger.info("Create new cart: {}", cartDto);
        Cart cart = cartMapper.toCart(cartDto);
        cart.setCreatedAt(new Date());
        return cartRepository.save(cart);
    }

    @Override
    public ApiResponseDto<CartInfoDto> getCartDetails(String cartId) {
        logger.info("Get cart: {}", cartId);
        double startTime = System.currentTimeMillis();
        Optional<Cart> cart = cartRepository.findById(cartId);
        if (cart.isEmpty()) {
            throw new CartNotFoundException("Cart not found.");
        }
        ArrayList<String> productCodeList = new ArrayList<>();
        for (ItemsDto item : cart.get().getItems()) {
            productCodeList.add(item.getProductCode());
        }
        ProductSearchRequestDto productSearchRequestDto = new ProductSearchRequestDto(productCodeList);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = catalogServiceBaseUrl + "/v1/products";
        logger.info("Calling catalog service url: {} with payload: {}", url, productSearchRequestDto);
        ResponseEntity<ApiResponseDto<List<Product>>> response = restTemplate.exchange(
            url,
            HttpMethod.POST,
            new HttpEntity<>(productSearchRequestDto, headers),
            new ParameterizedTypeReference<>() {}
        );
        logger.info("Finished calling catalog service. Response: {}", response.getBody());
        List<Product> products = new ArrayList<>();
        if (response.getBody() != null || response.getBody().getData() == null) {
            products = response.getBody().getData();
        }
        CartInfoDto cartInfoDto = new CartInfoDto(cartId, products);
        MetaDataDto metaDataDto = MetaDataDto.builder()
            .page(1)
            .size(1)
            .total(1)
            .took(System.currentTimeMillis() - startTime)
            .build();
        return new ApiResponseDto<>(cartInfoDto, "OK", null, metaDataDto);
    }
}
