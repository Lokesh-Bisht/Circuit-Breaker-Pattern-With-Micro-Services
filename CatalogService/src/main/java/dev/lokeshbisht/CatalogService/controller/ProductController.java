package dev.lokeshbisht.CatalogService.controller;

import dev.lokeshbisht.CatalogService.dto.ApiResponseDto;
import dev.lokeshbisht.CatalogService.dto.ProductDto;
import dev.lokeshbisht.CatalogService.entity.Product;
import dev.lokeshbisht.CatalogService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public Product createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @GetMapping("/product/{productId}")
    public ApiResponseDto<Product> getProductByProductId(@PathVariable Long productId) {
        return productService.getProductByProductId(productId);
    }

    @GetMapping("/product/code/{productCode}")
    public ApiResponseDto<Product> getProductByProductCode(@PathVariable String productCode) {
        return productService.getProductByProductCode(productCode);
    }
}
