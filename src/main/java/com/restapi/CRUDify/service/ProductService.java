package com.restapi.CRUDify.service;

import com.restapi.CRUDify.entity.Product;

import java.util.List;

public interface ProductService {

	Product saveProduct(Product product);

	List<Product> fetchAllProducts();

	Product getProductById(Long id);

	Product updateProductById(Long id, Product product);

	String deleteProductById(Long id);
}
