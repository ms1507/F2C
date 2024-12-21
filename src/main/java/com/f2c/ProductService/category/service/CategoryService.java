package com.f2c.ProductService.category.service;

import com.f2c.ProductService.category.model.Category;
import com.f2c.ProductService.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> fetchAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public Category modifyCategoryById(Long categoryId, Category updatedCategory) {
        return categoryRepository.findById(categoryId).map(existingCategory -> {
            existingCategory.setCategoryName(updatedCategory.getCategoryName());
            return categoryRepository.save(existingCategory);
        }).orElseThrow(() -> new RuntimeException("No category found for updation.."));
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void removeCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

}
