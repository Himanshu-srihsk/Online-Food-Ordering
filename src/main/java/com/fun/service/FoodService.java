package com.fun.service;

import com.fun.model.Category;
import com.fun.model.Food;
import com.fun.model.Restaurant;
import com.fun.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest req, Category category, Restaurant resturant);
    public void deleteFood(Long foodId) throws Exception;
    public List<Food> getResturantsFood(Long restaurantId, boolean isVegitarian,
                                        boolean isNonveg, boolean isSeasonal, String foodCategory);
    public List<Food> searchFood(String keyword);
    public Food findFoodById(Long foodId) throws Exception;
    public Food updateAvailability(Long foodId) throws Exception;

}
