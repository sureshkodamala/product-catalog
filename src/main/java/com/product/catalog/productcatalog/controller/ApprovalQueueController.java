package com.product.catalog.productcatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.catalog.productcatalog.service.ApprovalQueueService;

@RestController
@RequestMapping("/api/products")
public class ApprovalQueueController {
	
	@Autowired
	private ApprovalQueueService approvalQueueService;

	@PutMapping("/{approvalId}/approve")
	public ResponseEntity<?> approveProduct(@PathVariable Long approvalId) {
		try {
			approvalQueueService.approveProduct(approvalId);
			return ResponseEntity.ok().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/{approvalId}/reject")
	public ResponseEntity<?> rejectProduct(@PathVariable Long approvalId) {
		try {
			approvalQueueService.rejectProduct(approvalId);
			return ResponseEntity.ok().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}