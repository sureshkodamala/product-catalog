package com.product.catalog.productcatalog.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.catalog.productcatalog.entity.Product;
import com.product.catalog.productcatalog.util.ProductStatus;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByStatusOrderByCreatedAtDesc(ProductStatus status);
	
	@Query("SELECT p FROM Product p WHERE " +
            "(:productName IS NULL OR p.name LIKE %:productName%) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice) " +
            "AND (:minPostedDate IS NULL OR p.createdAt >= :minPostedDate) " +
            "AND (:maxPostedDate IS NULL OR p.createdAt <= :maxPostedDate)")
    List<Product> searchProducts(@Param("productName") String productName,
                                 @Param("minPrice") BigDecimal minPrice,
                                 @Param("maxPrice") BigDecimal maxPrice,
                                 @Param("minPostedDate") LocalDateTime minPostedDate,
                                 @Param("maxPostedDate") LocalDateTime  maxPostedDate);
}
