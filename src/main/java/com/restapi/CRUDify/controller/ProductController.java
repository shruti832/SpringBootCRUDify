package com.restapi.CRUDify.controller;

import com.restapi.CRUDify.entity.Product;
import com.restapi.CRUDify.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.*;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/products")
	public Product saveProduct(@RequestBody @Validated Product product) {
		return productService.saveProduct(product);
	}

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return productService.fetchAllProducts();
	}

	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable("id") Long id) {
		return productService.getProductById(id);
	}

	@PutMapping("/product/{id}")
	public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
		return productService.updateProductById(id, product);
	}

	@DeleteMapping("/product/{id}")
	public String deleteProduct(@PathVariable("id") Long id) {
		return productService.deleteProductById(id);
	}
}
