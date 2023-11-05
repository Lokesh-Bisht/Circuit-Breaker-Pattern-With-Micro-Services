package dev.lokeshbisht.CatalogService.service;

import dev.lokeshbisht.CatalogService.dto.ApiResponseDto;
import dev.lokeshbisht.CatalogService.dto.ProductDto;
import dev.lokeshbisht.CatalogService.entity.Product;

public interface ProductService {

    Product createProduct(ProductDto productDto);
    ApiResponseDto<Product> getProductByProductId(Long productId);
    ApiResponseDto<Product> getProductByProductCode(String productCode);
}
