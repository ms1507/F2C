package com.f2c.ProductService.product.controller;

import com.f2c.ProductService.product.service.ProductService;
import com.f2cUtility.common.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        logger.info("Received request to get all products...!!");
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        logger.info("Received request to get product information for {}", id);
        return productService.getProduct(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        logger.info("Received request to Update product information for {}", id);
        Product product = productService.updateProduct(id, updatedProduct);


        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        logger.info("Received request Add new PRODUCT...!!!");
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        logger.info("Received request to Delete product information for {}", id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/batch")
    public List<Product> getProductsByIds(@RequestParam List<Long> productIds) {
        logger.info("Received request to Get products By Ids for {}", productIds);
        return productService.getProductsByIds(productIds);
    }
}
