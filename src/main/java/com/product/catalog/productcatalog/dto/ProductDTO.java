package com.product.catalog.productcatalog.dto;

import java.math.BigDecimal;

import com.product.catalog.productcatalog.util.ProductStatus;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {

	private Long productId;
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than or equal to 0.01")
	@DecimalMax(value = "10000", message = "Price must not be greater than 10000")
	private BigDecimal price;
	
	private ProductStatus status;
	
}
