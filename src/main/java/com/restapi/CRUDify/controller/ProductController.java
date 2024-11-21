package com.restapi.CRUDify.controller;

import com.restapi.CRUDify.entity.Product;
import com.restapi.CRUDify.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/products")
	@Operation(summary = "Save a new product", description = "Create a new product and save it in the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Product created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid product details") })
	public Product saveProduct(@RequestBody @Validated Product product) {
		return productService.saveProduct(product);
	}

	@GetMapping("/products")
	@Operation(summary = "Fetch all products", description = "Retrieve a list of all products")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully retrieved list of products"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	public List<Product> getAllProducts() {
		return productService.fetchAllProducts();
	}

	@Operation(summary = "Get product by ID", description = "Retrieve a product by its ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Product retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "Product not found"),
			@ApiResponse(responseCode = "400", description = "Invalid product ID") })
	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable("id") Long id) {
		if (id < 0) {
			throw new IllegalArgumentException("Invalid user ID: " + id);
		}
		return productService.getProductById(id);
	}

	@Operation(summary = "Update product by ID", description = "Update the details of an existing product")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Product updated successfully"),
			@ApiResponse(responseCode = "404", description = "Product not found"),
			@ApiResponse(responseCode = "400", description = "Invalid product details") })
	@PutMapping("/product/{id}")
	public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
		return productService.updateProductById(id, product);
	}

	@Operation(summary = "Delete product by ID", description = "Delete an existing product from the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Product not found"),
			@ApiResponse(responseCode = "400", description = "Invalid product ID") })
	@DeleteMapping("/product/{id}")
	public String deleteProduct(@PathVariable("id") Long id) {
		return productService.deleteProductById(id);
	}
}
