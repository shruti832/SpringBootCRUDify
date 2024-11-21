package com.restapi.CRUDify.repository;

import com.restapi.CRUDify.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Product} entities.
 * 
 * This interface extends {@link JpaRepository} to provide CRUD operations on
 * the Product entity. It leverages Spring Data JPA to interact with the
 * database, automatically providing common methods such as save, findById,
 * delete, etc.
 * 
 * Custom query methods can be added here if needed in the future.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
