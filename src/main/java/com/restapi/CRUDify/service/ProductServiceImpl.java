package com.restapi.CRUDify.service;

import com.restapi.CRUDify.entity.Product;
import com.restapi.CRUDify.exceptionhandler.ProductNotFoundException;
import com.restapi.CRUDify.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> fetchAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			return product.get();
		} else 
			throw new ProductNotFoundException("Product not found with ID: " + id);
	}

	@Override
	public Product updateProductById(Long id, Product product) {
		Optional<Product> product1 = productRepository.findById(id);

		if (product1.isPresent()) {
			Product originalProduct = product1.get();

			if (Objects.nonNull(product.getProductName()) && !"".equalsIgnoreCase(product.getProductName())) {
				originalProduct.setProductName(product.getProductName());
			}
			if (Objects.nonNull(product.getProductPrice()) && product.getProductPrice() != 0) {
				originalProduct.setProductPrice(product.getProductPrice());
			}
			return productRepository.save(originalProduct);
		}
		return null;
	}

	@Override
	public String deleteProductById(Long id) {
		if (productRepository.findById(id).isPresent()) {
			productRepository.deleteById(id);
			return "Product deleted successfully";
		} else {
	        throw new ProductNotFoundException("Product not found with ID: " + id);
	    }
	}
}
