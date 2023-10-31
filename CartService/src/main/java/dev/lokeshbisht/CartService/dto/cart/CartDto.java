package dev.lokeshbisht.CartService.dto.cart;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.lokeshbisht.CartService.enums.CustomerType;

import java.util.Date;
import java.util.List;

import static dev.lokeshbisht.CartService.constants.JsonConstants.ISO8601;

public class CartDto {

    @JsonProperty("customer_id")
    private String customerId;

    @JsonProperty("customer_type")
    private CustomerType customerType;

    private String operation;

    private List<ItemsDto> items;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ISO8601)
    @JsonProperty("created_at")
    private Date createdAt;
}
