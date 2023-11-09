package dev.lokeshbisht.CartService.dto.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.lokeshbisht.CartService.dto.catalog.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CartInfoDto {

    @JsonProperty("cart_id")
    private String cartId;

    private List<Product> items;
}
