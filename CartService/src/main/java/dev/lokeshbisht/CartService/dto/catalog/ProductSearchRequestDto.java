package dev.lokeshbisht.CartService.dto.catalog;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchRequestDto {

    @JsonProperty("product_codes")
    private List<String> productCodeList;
}
