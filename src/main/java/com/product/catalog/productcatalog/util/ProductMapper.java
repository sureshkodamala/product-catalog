package com.product.catalog.productcatalog.util;

import com.product.catalog.productcatalog.dto.ProductDTO;
import com.product.catalog.productcatalog.entity.Product;

public class ProductMapper {
	
	public static ProductDTO toDto(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		productDTO.setStatus(product.getStatus());
		return productDTO;
	}
	
	public static Product toEntity(ProductDTO productDto) {
		Product product = new Product();
		product.setId(productDto.getProductId());
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setStatus(productDto.getStatus());
		return product;
	}

}
