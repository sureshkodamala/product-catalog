package com.product.catalog.productcatalog.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.catalog.productcatalog.dto.ProductDTO;
import com.product.catalog.productcatalog.dto.ProductResponse;
import com.product.catalog.productcatalog.service.ProductService;
import com.product.catalog.productcatalog.util.ResponseStatus;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ProductResponse> listActiveProducts() {
        List<ProductDTO> activeProducts = productService.listActiveProductsSortedByLatestFirst();
        ProductResponse resp = new ProductResponse();
        resp.setStatusCode(HttpStatus.OK.value());
        resp.setStatus(ResponseStatus.SUCCESS);
        resp.setMessage("No Active Products found");
        if(!activeProducts.isEmpty()) {
        	resp.setResponse(activeProducts);
        	resp.setMessage("Successfully Retrived Active Products");
        }
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            productService.createProduct(productDTO);
            ProductResponse resp = new ProductResponse();
            resp.setStatusCode(HttpStatus.CREATED.value());
            resp.setStatus(ResponseStatus.SUCCESS);
            resp.setMessage("Product Successfully created");
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
        	throw e;
        }
    }
    
    @GetMapping("/search")
    public ResponseEntity<ProductResponse> searchProducts(@RequestParam(required = false) String productName,
                                                         @RequestParam(required = false) BigDecimal minPrice,
                                                         @RequestParam(required = false) BigDecimal maxPrice,
                                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime  minPostedDate,
                                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime  maxPostedDate) {
        List<ProductDTO> searchedProducts = productService.searchProducts(productName, minPrice, maxPrice, minPostedDate, maxPostedDate);
        ProductResponse resp = new ProductResponse();
        resp.setStatusCode(HttpStatus.OK.value());
        resp.setStatus(ResponseStatus.SUCCESS);
        resp.setMessage("No Matched Products found");
        if(!searchedProducts.isEmpty()) {
        	resp.setResponse(searchedProducts);
        	resp.setMessage("Successfully Retrived Products");
        }
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }
/*
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
        try {
            productService.updateProduct(productId, productDTO);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }*/
}