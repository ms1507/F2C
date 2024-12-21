package com.f2c.ProductService.category.controller;

import com.f2c.ProductService.category.model.Category;
import com.f2c.ProductService.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class CategoryController {

    @Autowired(required = true)
    CategoryService categoryService;

    @GetMapping("/categoryies")
    public ResponseEntity<List<Category>> getCategories() {

        return ResponseEntity.ok(categoryService.fetchAllCategories());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        return categoryService.getCategoryById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        return ResponseEntity.ok(categoryService.modifyCategoryById(id, updatedCategory));
    }
    
    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.addCategory(category));
    }
    
        @DeleteMapping("/category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.getCategoryById(id)
            .ifPresent(category -> categoryService.removeCategory(id));
        return ResponseEntity.noContent().build();
    }
}
