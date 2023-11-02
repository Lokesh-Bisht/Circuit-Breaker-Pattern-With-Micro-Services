package dev.lokeshbisht.CatalogService.service.impl;

import dev.lokeshbisht.CatalogService.dto.ProductDto;
import dev.lokeshbisht.CatalogService.entity.Product;
import dev.lokeshbisht.CatalogService.mapper.ProductMapper;
import dev.lokeshbisht.CatalogService.repository.ProductRepository;
import dev.lokeshbisht.CatalogService.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
