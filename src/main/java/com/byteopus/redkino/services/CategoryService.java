package com.byteopus.redkino.services;

import com.byteopus.redkino.models.Category;
import com.byteopus.redkino.models.Director;
import com.byteopus.redkino.models.Movie;
import com.byteopus.redkino.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public void save(Category category) {
        categoryRepository.save(category);
    }
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
    public void deleteById(Long id)
    {
        this.categoryRepository.deleteById(id);
    }
    public List<Category> searchByName(String name) {
        return this.categoryRepository.searchByName(name);
    }
    public Category findById(Long categoryId) {
        return this.categoryRepository.findById(categoryId).orElse(null);
    }
}

