package com.fun.service;

import com.fun.model.Category;
import com.fun.model.Resturant;
import com.fun.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private ResturantService resturantService;
    @Autowired
    public CategoryRepository categoryRepository;
    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Resturant resturant = resturantService.getResturantByUserId(userId);
        Category category = new Category();
        category.setName(name);
        category.setResturant(resturant);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByResturantId(Long id) throws Exception {
        // Resturant resturant = resturantService.getResturantByUserId(id);
        return categoryRepository.findByResturantId(id);
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new Exception("Category not found with id "+id);
        }
        return category.get();
    }
}
