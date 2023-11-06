package dev.lokeshbisht.CatalogService.service;

import dev.lokeshbisht.CatalogService.dto.ApiResponseDto;
import dev.lokeshbisht.CatalogService.dto.ProductDto;
import dev.lokeshbisht.CatalogService.entity.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(ProductDto productDto);
    ApiResponseDto<Product> getProductByProductId(Long productId);
    ApiResponseDto<Product> getProductByProductCode(String productCode);
    ApiResponseDto<List<Product>>getProducts(List<String> productCodeList);
}
