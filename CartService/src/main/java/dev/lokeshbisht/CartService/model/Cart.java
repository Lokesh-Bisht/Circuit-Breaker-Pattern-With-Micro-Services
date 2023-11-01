package dev.lokeshbisht.CartService.model;

import dev.lokeshbisht.CartService.dto.cart.ItemsDto;
import dev.lokeshbisht.CartService.enums.CustomerType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;
import java.util.List;

@Data
@Builder
@RedisHash
public class Cart {

    @Id
    private String cartId;

    private String customerId;

    private CustomerType customerType;

    private String operation;

    private List<ItemsDto> items;

    private String createdBy;

    private Date createdAt;

    private String updatedBy;

    private Date updatedAt;
}
