package com.product.catalog.productcatalog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.product.catalog.productcatalog.dto.ProductDTO;
import com.product.catalog.productcatalog.entity.Product;

@Mapper
@Component
public interface ProductMapper {

	@Mapping(target = "productId", source = "product.id")
	@Mapping(target = "name", source = "product.name")
	@Mapping(target = "price", source = "product.price")
	@Mapping(target = "status", source = "product.status")
	ProductDTO toDto(Product product);

	@Mapping(target = "id", source = "productDto.productId")
	@Mapping(target = "name", source = "productDto.name")
	@Mapping(target = "price", source = "productDto.price")
	@Mapping(target = "status", source = "productDto.status")
	Product toEntity(ProductDTO productDto);

}