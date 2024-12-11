package com.f2c.ProductService.product.service;

import com.f2c.ProductService.product.model.Product;
import com.f2c.ProductService.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Optional<Product> getProduct(Long productId) {
        return productRepository.findById(productId);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setCategoryId(updatedProduct.getCategoryId());
            existingProduct.setMeasurementId(updatedProduct.getMeasurementId());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setUpdatedAt(LocalDateTime.now());
            return productRepository.save(existingProduct);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
