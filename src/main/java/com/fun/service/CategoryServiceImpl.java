package com.fun.service;

import com.fun.model.Category;
import com.fun.model.Restaurant;
import com.fun.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    public CategoryRepository categoryRepository;
    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Restaurant resturant = restaurantService.getResturantByUserId(userId);
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(resturant);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByResturantId(Long id) throws Exception {
         Restaurant resturant = restaurantService.findResturantById(id);
        return categoryRepository.findByRestaurantId(id);
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
