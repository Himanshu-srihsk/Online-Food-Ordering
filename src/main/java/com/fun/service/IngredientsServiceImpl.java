package com.fun.service;

import com.fun.model.IngredientsCategory;
import com.fun.model.IngredientsItem;
import com.fun.model.Resturant;
import com.fun.repository.IngredientsCategoryRepository;
import com.fun.repository.IngredientsItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsServiceImpl implements IngredientsService{
    @Autowired
    private IngredientsItemRepository ingredientsItemRepository;
    @Autowired
    private IngredientsCategoryRepository ingredientsCategoryRepository;
    @Autowired
    private ResturantService resturantService;

    @Override
    public IngredientsCategory createIngredientCategory(String name, Long resturantId) throws Exception {
        Resturant resturant = resturantService.findResturantById(resturantId);
        IngredientsCategory category = new IngredientsCategory();
        System.out.println("name: " + name +    " resturantId: " + resturantId);
        category.setName(name);
        category.setResturant(resturant);
        return ingredientsCategoryRepository.save(category);
    }

    @Override
    public IngredientsCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientsCategory> opt = ingredientsCategoryRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("Ingredient Category not found with id "+id);
        }
        return opt.get();
    }

    @Override
    public List<IngredientsCategory> findIngredientCategoryByResturantId(Long id) throws Exception {
        resturantService.findResturantById(id);
        return ingredientsCategoryRepository.findByResturantId(id);
    }

    @Override
    public List<IngredientsItem> findResturantIngredient(Long resturantId) throws Exception {
        return ingredientsItemRepository.findByResturantId(resturantId);
    }

    @Override
    public IngredientsItem createIngredientItem(Long resturantId, String ingredientName, Long categoryId) throws Exception {
        Resturant resturant = resturantService.findResturantById(resturantId);
        IngredientsCategory category = findIngredientCategoryById(categoryId);
        IngredientsItem item = new IngredientsItem();
        item.setName(ingredientName);
        item.setResturant(resturant);
        item.setCategory(category);
        IngredientsItem ingredientsItem = ingredientsItemRepository.save(item);
        category.getIngredients().add(ingredientsItem);
        return ingredientsItem;
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> optionalIngredientsItem = ingredientsItemRepository.findById(id);
        if(optionalIngredientsItem.isEmpty()){
            throw new Exception("Ingredient Item not found with id "+id);
        }
        IngredientsItem ingredientsItem = optionalIngredientsItem.get();
        ingredientsItem.setInStock(!ingredientsItem.isInStock());
        return ingredientsItemRepository.save(ingredientsItem);
    }
}
