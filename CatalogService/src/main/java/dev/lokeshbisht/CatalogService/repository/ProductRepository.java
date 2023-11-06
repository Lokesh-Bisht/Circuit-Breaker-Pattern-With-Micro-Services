package dev.lokeshbisht.CatalogService.repository;

import dev.lokeshbisht.CatalogService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductCode(String productCode);
    List<Product> findByProductCodeIn(List<String> productCodeList);
}
