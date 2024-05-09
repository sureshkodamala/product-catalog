package com.product.catalog.productcatalog.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.catalog.productcatalog.dto.ProductDTO;
import com.product.catalog.productcatalog.entity.ApprovalQueue;
import com.product.catalog.productcatalog.entity.Product;
import com.product.catalog.productcatalog.repository.ProductRepository;
import com.product.catalog.productcatalog.util.ApprovalStatus;
import com.product.catalog.productcatalog.util.ProductMapper;
import com.product.catalog.productcatalog.util.ProductStatus;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ApprovalQueueService approvalQueueService;

	public List<ProductDTO> listActiveProductsSortedByLatestFirst() {
		List<Product> products = productRepository.findByStatusOrderByCreatedAtDesc(ProductStatus.ACTIVE);
		return products.stream().map(product -> ProductMapper.toDto(product)).collect(Collectors.toList());

	}

	@Transactional
	public void createProduct(ProductDTO productDto) {
		Product productOut = null;
		Product product = ProductMapper.toEntity(productDto);
		if (product.getPrice().compareTo(BigDecimal.valueOf(5000)) > 0) {
			product.setStatus(ProductStatus.PENDING_APPROVAL);
			product.setApprovalStatus(ApprovalStatus.PENDING);
			productOut = productRepository.save(product);
			ApprovalQueue queue = new ApprovalQueue();
			queue.setProductId(productOut.getId());
			approvalQueueService.saveApprovalQueue(queue);
		} else {
			product.setStatus(ProductStatus.ACTIVE);
			product.setApprovalStatus(ApprovalStatus.APPROVED);
			productOut = productRepository.save(product);
		}
	}

	public List<ProductDTO> searchProducts(String productName, BigDecimal minPrice, BigDecimal maxPrice,
			LocalDateTime minPostedDate, LocalDateTime maxPostedDate) {
		List<Product> products = productRepository.searchProducts(productName, minPrice, maxPrice, minPostedDate, maxPostedDate);
		return products.stream().map(product -> ProductMapper.toDto(product)).collect(Collectors.toList());
	}

	/*
	 * public void createProduct(ProductRequest request) { // Implement creation
	 * logic, handle price validation and approval queue }
	 * 
	 * public void updateProduct(Long productId, ProductRequest request) { //
	 * Implement update logic, handle price validation and approval queue }
	 * 
	 * public void deleteProduct(Long productId) { // Implement delete logic, move
	 * product to approval queue }
	 */
}