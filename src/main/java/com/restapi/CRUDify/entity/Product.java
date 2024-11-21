package com.restapi.CRUDify.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Represents a product in the system")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(description = "Unique identifier of the product", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
	private Long productId;

	@Column(nullable = false)
	@Size(min = 3, max = 100, message = "Product name must be between 3 and 100 characters")
    @Schema(description = "Name of the product", example = "Wireless Mouse")
	private String productName;

	@DecimalMin(value = "0.0", message = "Price must be a positive value")
	@Schema(description = "Price of the product", example = "25.99")
	private float productPrice;

	@Size(max = 200, message = "Description should not exceed 200 characters")
	@Schema(description = "Short description of the product", example = "A high-quality wireless mouse with ergonomic design")
	private String productDescription;

	public Product() {
	}

	public Product(Long productId, String productName, float productPrice, String productDescription) {
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
}
