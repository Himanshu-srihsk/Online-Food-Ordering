package com.fun.service;

import com.fun.model.Category;
import com.fun.model.Food;
import com.fun.model.Restaurant;
import com.fun.repository.FoodRepository;
import com.fun.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService{
    @Autowired
    private FoodRepository foodRepository;
    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant resturant) {
        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(resturant);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        food.setIngredientsItems(req.getIngredientsItems());
        food.setSeasonal(req.isSeasonal());
        food.setVegeterian(req.isVegetarian());
        food.setCreationDate(new Date());


        Food savedFood= foodRepository.save(food);
        resturant.getFoods().add(savedFood);
        return savedFood;

    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);
    }

    @Override
    public List<Food> getResturantsFood(Long restaurantId, boolean isVegitarian,
                                        boolean isNonveg, boolean isSeasonal, String foodCategory) {
       // System.out.println("Food list before retrieved from database: " + restaurantId+ " isVe="+ isVegitarian);
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

       // System.out.println("Food list retrieved from database: " + foods);

        if(isVegitarian){
            foods = filterByVegetarian(foods,isVegitarian);
        }
        if(isNonveg){
            foods = filterByNonVegetarian(foods,isNonveg);
        }
        if(isSeasonal){
            foods = filterBySeasonal(foods,isSeasonal);
        }
        if(foodCategory!=null && !foodCategory.isEmpty()){
            foods = filterByCategory(foods,foodCategory);
        }
        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if(food.getFoodCategory()!=null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal()==isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonVegetarian(List<Food> foods, boolean isNonveg) {
        return foods.stream().filter(food -> !food.isVegeterian()).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegitarian) {
        return foods.stream().filter(food -> food.isVegeterian()==isVegitarian).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalFood = foodRepository.findById(foodId);
        if (optionalFood.isEmpty()){
            throw new Exception("Food not found with id "+foodId);
        }
        return optionalFood.get();
    }

    @Override
    public Food updateAvailability(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
