package com.product.catalog.productcatalog.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.product.catalog.productcatalog.util.ResponseStatus;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
	
	private int statusCode;
	private ResponseStatus status;
	private String message;
	private List<Error> errors;
	private Object response;
}
