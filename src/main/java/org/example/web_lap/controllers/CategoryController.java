package org.example.web_lap.controllers;

import jakarta.validation.Valid;
import org.example.web_lap.dtos.fillter.CategoryParam;
import org.example.web_lap.dtos.request.CategoryRequest;
import org.example.web_lap.entities.Category;
import org.example.web_lap.repository.CategoryReposirory;
import org.example.web_lap.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Category createCategory(@Valid @RequestBody CategoryRequest request) {
        return categoryService.save(request);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping ("/{id}")
    public Category updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        return categoryService.update(id, request);
    }

    @GetMapping
    public Page<Category> filler(CategoryParam param , Pageable pageable) {
        return categoryService.filter(param, pageable);
    }
}
