package com.product.catalog.productcatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.catalog.productcatalog.entity.ApprovalQueue;

public interface ApprovalQueueRepository extends JpaRepository<ApprovalQueue, Long> {
    // Add custom queries as needed
}