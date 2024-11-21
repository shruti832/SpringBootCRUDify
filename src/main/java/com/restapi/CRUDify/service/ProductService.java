package com.restapi.CRUDify.service;

import com.restapi.CRUDify.entity.Product;

import java.util.List;

/**
 * Service interface for handling product-related operations.
 * This interface defines the methods to manage the lifecycle of products in the system.
 */
public interface ProductService {

	/**
     * Saves a new product or updates an existing product in the database.
     * 
     * @param product The product entity to be saved or updated.
     * @return The saved or updated product entity.
     */
	Product saveProduct(Product product);

	/**
     * Retrieves all products from the database.
     * 
     * @return A list of all products in the system.
     */
	List<Product> fetchAllProducts();

	/**
     * Retrieves a product by its unique ID.
     * 
     * @param id The unique identifier of the product.
     * @return The product entity corresponding to the provided ID.
     * @throws ProductNotFoundException If the product with the specified ID is not found.
     */
	Product getProductById(Long id);

	/**
     * Updates the details of an existing product in the database.
     * 
     * @param id The unique identifier of the product to be updated.
     * @param product The product entity containing the updated details.
     * @return The updated product entity.
     * @throws ProductNotFoundException If the product with the specified ID is not found.
     */
	Product updateProductById(Long id, Product product);

	/**
     * Deletes a product from the database by its unique ID.
     * 
     * @param id The unique identifier of the product to be deleted.
     * @return A message indicating the success or failure of the delete operation.
     * @throws ProductNotFoundException If the product with the specified ID is not found.
     */
	String deleteProductById(Long id);
}
