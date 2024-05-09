package com.product.catalog.productcatalog.exception;

import java.util.Arrays;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.product.catalog.productcatalog.dto.Error;
import com.product.catalog.productcatalog.dto.ProductResponse;

@RestControllerAdvice
public class ProductCatalogExceptionHandler {
	
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ProductResponse> handleBadRequestException(BadRequestException ex) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, "Bad Request", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProductResponse> handleInternalServerError(Exception ex) {
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage());
    }

    private ResponseEntity<ProductResponse> createErrorResponse(HttpStatus status, String errorType, String message) {
        ProductResponse response = new ProductResponse();
        response.setStatusCode(status.value());
        response.setStatus(com.product.catalog.productcatalog.util.ResponseStatus.FAILED);
        response.setMessage(message);
        Error error = new Error();
        error.setErrorType(errorType);
        error.setErrorDescription(message);
        response.setErrors(Arrays.asList(error));
        return ResponseEntity.status(status).body(response);
    }

}
