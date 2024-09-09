package com.fun.service;

import com.fun.model.IngredientsCategory;
import com.fun.model.IngredientsItem;

import java.util.List;

public interface IngredientsService {
    public IngredientsCategory createIngredientCategory(String name, Long resturantId) throws Exception;
    public IngredientsCategory findIngredientCategoryById(Long id) throws Exception;
    public List<IngredientsCategory> findIngredientCategoryByResturantId(Long id) throws Exception;
    public List<IngredientsItem> findResturantIngredient(Long resturantId) throws Exception;
    public IngredientsItem createIngredientItem(Long resturantId, String ingredientName, Long categoryId) throws Exception;
    public IngredientsItem updateStock(Long id) throws Exception;
}
