package com.product.catalog.productcatalog.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.product.catalog.productcatalog.util.ApprovalStatus;
import com.product.catalog.productcatalog.util.ProductStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private BigDecimal price;

	@Enumerated(EnumType.STRING)
	private ProductStatus status;
	
	@Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	 @PrePersist
	  protected void onCreate() {
		 createdAt = LocalDateTime.now();
	  }

	  @PreUpdate
	  protected void onUpdate() {
		  updatedAt = LocalDateTime.now();
	  }
}
