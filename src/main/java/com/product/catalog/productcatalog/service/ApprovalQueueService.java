package com.product.catalog.productcatalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.catalog.productcatalog.entity.ApprovalQueue;
import com.product.catalog.productcatalog.repository.ApprovalQueueRepository;

@Service
public class ApprovalQueueService {

    @Autowired
    private ApprovalQueueRepository approvalQueueRepository;
    
    public void saveApprovalQueue(ApprovalQueue approvalQueue) {
    	approvalQueue.setApprovalId(Math.random());
    	approvalQueueRepository.save(approvalQueue);
    }

    public List<ApprovalQueue> getProductsInApprovalQueue() {
		return null;
        // Implement logic to retrieve products in approval queue
    }

    public void approveProduct(Long approvalId) {
        // Implement logic to approve product and update its status
    }

    public void rejectProduct(Long approvalId) {
        // Implement logic to reject product and remove it from approval queue
    }
}