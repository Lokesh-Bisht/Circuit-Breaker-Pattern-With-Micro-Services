package dev.lokeshbisht.CatalogService.mapper;

import dev.lokeshbisht.CatalogService.dto.ProductDto;
import dev.lokeshbisht.CatalogService.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductDto productDto);

    @Mapping(target = "productId", source = "id")
    ProductDto toProductDto(Product product);
}
