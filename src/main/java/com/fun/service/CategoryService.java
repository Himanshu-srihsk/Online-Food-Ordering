package com.fun.service;

import com.fun.model.Category;

import java.util.List;

public interface CategoryService {
    public Category createCategory(String name, Long userId) throws Exception;
    public List<Category> findCategoryByResturantId(Long resturantId) throws Exception;
    public Category findCategoryById(Long id) throws Exception;
}
