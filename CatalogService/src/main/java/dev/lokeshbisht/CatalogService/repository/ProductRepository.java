package dev.lokeshbisht.CatalogService.repository;

import dev.lokeshbisht.CatalogService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
