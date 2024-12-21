package com.f2c.ProductService.product.repository;

import com.f2c.ProductService.product.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    // Custom methods
    //public Product getProductById(Long productId);
}
