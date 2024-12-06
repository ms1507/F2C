package com.f2c.ProductService.controller;

import com.f2c.ProductService.model.Category;
import com.f2c.ProductService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categoryies")
    public ResponseEntity<List<Category>> getCategories() {

        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        return categoryService.getCategoryById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        return ResponseEntity.ok(categoryService.updateCategoryById(id, updatedCategory));
    }
    
    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }
    
        @DeleteMapping("/category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.getCategoryById(id)
            .ifPresent(category -> categoryService.deleteCategory(id));
        return ResponseEntity.noContent().build();
    }
}
