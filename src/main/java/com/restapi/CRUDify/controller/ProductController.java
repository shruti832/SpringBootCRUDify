package com.restapi.CRUDify.controller;

import com.restapi.CRUDify.entity.Product;
import com.restapi.CRUDify.service.ProductService;
import com.restapi.CRUDify.exceptionhandler.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;

import java.util.List;

/**
 * Controller class for managing products in the system. Provides endpoints for
 * creating, retrieving, updating, and deleting products.
 */
@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * Endpoint to create a new product and save it in the database.
	 * 
	 * @param product The product object to be created.
	 * @return The created product.
	 */
	@PostMapping("/products")
	@Operation(summary = "Save a new product", description = "Create a new product and save it in the database")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "201", description = "Product created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
		    @ApiResponse(responseCode = "400", description = "Invalid product details", content = @Content(mediaType = "application/json"))
		})
	public Product saveProduct(@RequestBody @Validated Product product, HttpServletResponse response) {
		Product savedProduct = productService.saveProduct(product);

		if (savedProduct != null) {
			response.setStatus(HttpServletResponse.SC_CREATED); // Set 201 only on success
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set 400 if the product creation fails
		}
		return savedProduct;
	}

	/**
	 * Endpoint to retrieve all products from the database.
	 * 
	 * @return A list of all products.
	 */
	@GetMapping("/products")
	@Operation(summary = "Fetch all products", description = "Retrieve a list of all products")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully retrieved list of products"),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
	public List<Product> getAllProducts() {
		return productService.fetchAllProducts();
	}

	/**
	 * Endpoint to retrieve a product by its ID.
	 * 
	 * @param id The ID of the product to retrieve.
	 * @return The product with the specified ID.
	 * @throws ResourceNotFoundException if no product is found with the specified
	 *                                   ID.
	 */
	@GetMapping("/products/{id}")
	@Operation(summary = "Get product by ID", description = "Retrieve a product by its ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Product retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "Product not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid product ID", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
	public Product getProductById(@PathVariable Long id) {
		if (id < 0) {
			throw new IllegalArgumentException("Invalid user ID: " + id);
		}
		Product product = productService.getProductById(id);
		if (product == null) {
			throw new ResourceNotFoundException("Product not found with ID: " + id);
		}
		return product;
	}

	/**
	 * Endpoint to update the details of an existing product.
	 * 
	 * @param id      The ID of the product to update.
	 * @param product The updated product details.
	 * @return The updated product.
	 */
	@PutMapping("/product/{id}")
	@Operation(summary = "Update product by ID", description = "Update the details of an existing product")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Product updated successfully"),
			@ApiResponse(responseCode = "404", description = "Product not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid product details", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
	public Product updateProduct(@PathVariable Long id, @RequestBody @Validated Product product) {
		return productService.updateProductById(id, product);
	}

	/**
	 * Endpoint to delete a product from the database by its ID.
	 * 
	 * @param id The ID of the product to delete.
	 * @return A success message indicating that the product has been deleted.
	 * @throws ResourceNotFoundException if no product is found with the specified
	 *                                   ID.
	 */
	@DeleteMapping("/product/{id}")
	@Operation(summary = "Delete product by ID", description = "Delete an existing product from the database")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Product deleted successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class), examples = @ExampleObject(value = """
					    {
					      "message": "Product deleted successfully",
					      "statusCode": 200
					    }
					"""))),
			@ApiResponse(responseCode = "404", description = "Product not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(value = """
					    {
					      "message": "Product not found with ID: 123",
					      "statusCode": 404,
					      "error": "Not Found"
					    }
					"""))) })
	public String deleteProduct(@PathVariable Long id) {
		String resultMessage = productService.deleteProductById(id);

		if (resultMessage.equalsIgnoreCase("Product not found")) {
			throw new ResourceNotFoundException("Product not found with ID: " + id);
		}
		return resultMessage;
	}
}
