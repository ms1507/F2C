package com.f2c.ProductService.product.controller;

import com.f2c.ProductService.product.model.ProductEntity;
import com.f2c.ProductService.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/f2c/products")
public class ProductController {
    // Add product related RESTful API endpoints here
    // For example, getProductById, getAllProducts, createProduct, updateProduct, deleteProduct
    // Implement business logic to interact with the product repository
    // Use the ProductService to handle the business logic
    // Use ResponseEntity to return HTTP responses
    // Use appropriate HTTP status codes for different scenarios
    // Use ExceptionHandling mechanisms to handle potential errors
    // Use Swagger or other documentation tools to generate API documentation
    // Implement authentication and authorization mechanisms to secure the API endpoints
    // Implement caching mechanisms to improve performance
    // Implement rate limiting to prevent abuse
    // Implement logging and monitoring mechanisms to track API usage and performance
    // Implement security measures to protect the API endpoints from unauthorized access
    // Implement security measures to protect the API endpoints from potential data breaches
    // Implement security measures to protect the API endpoints from potential attacks
    // Implement security measures to protect the API endpoints from potential data leakage
    // Implement security measures to protect the API endpoints from potential SQL injection attacks
    // Implement security measures to protect the API endpoints from potential cross-site scripting (XSS) attacks
    // Implement security measures to protect the API endpoints from potential cross-site request forgery (CSRF) attacks
    // Implement security measures to protect the API endpoints from potential clickjacking attacks
    // Implement security measures to protect the API endpoints from potential XSS attacks

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable Long id) {
        return productService.getProduct(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable Long productId, @RequestBody ProductEntity updatedProduct) {
        ProductEntity product = productService.updateProduct(productId, updatedProduct);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/product")
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/batch")
    public List<ProductEntity> getProductsByIds(@RequestParam List<Long> productIds){
        return productService.getProductsByIds(productIds);
    }
}