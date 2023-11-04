package dev.lokeshbisht.CatalogService.service.impl;

import dev.lokeshbisht.CatalogService.dto.ApiResponseDto;
import dev.lokeshbisht.CatalogService.dto.MetaDataDto;
import dev.lokeshbisht.CatalogService.dto.ProductDto;
import dev.lokeshbisht.CatalogService.entity.Product;
import dev.lokeshbisht.CatalogService.exceptions.ProductNotFoundException;
import dev.lokeshbisht.CatalogService.mapper.ProductMapper;
import dev.lokeshbisht.CatalogService.repository.ProductRepository;
import dev.lokeshbisht.CatalogService.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public Product createProduct(ProductDto productDto) {
        logger.info("Start createProduct(): Creating a new product: {}", productDto);
        Product product = productMapper.toProduct(productDto);
        product.setCreatedAt(new Date());
        return productRepository.save(product);
    }

    private ApiResponseDto<Product> generateApiResponseDto(Product product, double startTime) {
        MetaDataDto metaDataDto = MetaDataDto.builder()
            .page(1)
            .size(1)
            .total(1)
            .took(System.currentTimeMillis() - startTime)
            .build();
        return new ApiResponseDto<>(product, "OK", null, metaDataDto);
    }

    @Override
    public ApiResponseDto<Product> getProductByProductId(Long productId) {
        logger.info("Get product by product_id : {}", productId);
        double startTime = System.currentTimeMillis();
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product not found!");
        }
        return generateApiResponseDto(product.get(), startTime);
    }
}
