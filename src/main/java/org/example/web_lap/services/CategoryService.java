package org.example.web_lap.services;

import org.example.web_lap.dtos.fillter.CategoryParam;
import org.example.web_lap.dtos.request.CategoryRequest;
import org.example.web_lap.entities.Category;
import org.example.web_lap.repository.CategoryReposirory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
    @Autowired
    private CategoryReposirory categoryReposirory;

    @Transactional
    public Category save(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        return categoryReposirory.save(category);
    }

    public Category findById(Long id) {
        return categoryReposirory.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
    @Transactional
    public void deleteById(Long id) {
        Category category = findById(id);
        category.setIsDeleted(true);
        categoryReposirory.save(category);
    }

    @Transactional
    public Category update(Long id, CategoryRequest request) {
        Category category = findById(id);
        category.setName(request.getName());
        return categoryReposirory.save(category);
    }

    public Page<Category> filter(CategoryParam param , Pageable pageable){
        return categoryReposirory.filter(param, pageable);
    }








}
