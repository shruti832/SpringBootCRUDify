package com.restapi.CRUDify.service;

import com.restapi.CRUDify.entity.Product;
import com.restapi.CRUDify.exceptionhandler.ProductNotFoundException;
import com.restapi.CRUDify.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	/**
     * Saves a new product to the repository.
     * 
     * @param product The product to be saved.
     * @return The saved product.
     */
	@Override
	public Product saveProduct(Product product) {
		if (product == null || product.getProductName() == null || product.getProductPrice() <= 0) {
	        throw new IllegalArgumentException("Product or required fields (name, price) cannot be null");
	    }
		return productRepository.save(product);
	}

	/**
     * Fetches all products from the repository.
     * 
     * @return A list of all products.
     */
	@Override
	public List<Product> fetchAllProducts() {
		return productRepository.findAll();
	}

	/**
     * Retrieves a product by its ID.
     * If the product is not found, a ProductNotFoundException is thrown.
     * 
     * @param id The ID of the product to retrieve.
     * @return The product with the given ID.
     * @throws ProductNotFoundException If the product with the given ID is not found.
     */
	@Override
	public Product getProductById(Long id) {
		if (id == null || id <= 0) {
	        throw new IllegalArgumentException("Invalid product ID");
	    }
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			return product.get();
		} else 
			throw new ProductNotFoundException("Product not found with ID: " + id);
	}

	/**
     * Updates the product with the given ID.
     * If the product exists, its details are updated with the provided data.
     * 
     * @param id The ID of the product to update.
     * @param product The product object containing the updated data.
     * @return The updated product, or null if no product was found with the given ID.
     */
	@Override
	public Product updateProductById(Long id, Product product) {
		if (id == null || id <= 0) {
	        throw new IllegalArgumentException("Invalid product ID");
	    }

	    if (product == null) {
	        throw new IllegalArgumentException("Product data cannot be null");
	    }
		Optional<Product> product1 = productRepository.findById(id);

		if (product1.isPresent()) {
			Product originalProduct = product1.get();
			
			if (product.getProductName() != null && !product.getProductName().trim().isEmpty()) {
				originalProduct.setProductName(product.getProductName());
			}

			if (product.getProductPrice() > 0) {
				originalProduct.setProductPrice(product.getProductPrice());
			}
			return productRepository.save(originalProduct);
		} else {
			throw new ProductNotFoundException("Product not found with ID: " + id);
		}
	}

	/**
     * Deletes the product with the given ID.
     * If the product exists, it is deleted from the repository.
     * 
     * @param id The ID of the product to delete.
     * @return A success message indicating the product was deleted.
     * @throws ProductNotFoundException If the product with the given ID is not found.
     */
	@Override
	public String deleteProductById(Long id) {
		 if (id == null || id <= 0) {
		        throw new IllegalArgumentException("Invalid product ID");
		    }

		if (productRepository.findById(id).isPresent()) {
			productRepository.deleteById(id);
			return "Product deleted successfully";
		} else {
	        throw new ProductNotFoundException("Product not found with ID: " + id);
	    }
	}
}
