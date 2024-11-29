package com.f2c.ProductService.repository;

import com.f2c.ProductService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    // Custom methods
    //public Product getProductById(Long productId);
}
