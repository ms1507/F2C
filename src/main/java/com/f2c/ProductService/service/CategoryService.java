package com.f2c.ProductService.service;

import com.f2c.ProductService.model.Category;
import com.f2c.ProductService.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public Category updateCategoryById(Long categoryId, Category updatedCategory) {
        return categoryRepository.findById(categoryId).map(existingCategory -> {
            existingCategory.setCategoryName(updatedCategory.getCategoryName());
            return categoryRepository.save(existingCategory);
        }).orElseThrow(() -> new RuntimeException("No category found for updation.."));
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

}
