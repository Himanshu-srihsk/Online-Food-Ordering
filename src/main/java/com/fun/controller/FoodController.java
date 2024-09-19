package com.fun.controller;

import com.fun.model.Food;
import com.fun.model.User;
import com.fun.service.FoodService;
import com.fun.service.RestaurantService;
import com.fun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foods = foodService.searchFood(name);
        return new ResponseEntity<>(foods, HttpStatus.CREATED);
    }
    @GetMapping("/resturant/{resturantId}")
    public ResponseEntity<List<Food>> getResturantFood(
                 @RequestParam (required = false) boolean vegetarian,
                 @RequestParam (required = false) boolean seasonal,
                 @RequestParam (required = false) boolean nonveg,
                 @RequestParam (required = false) String food_category,
                 @PathVariable Long resturantId,
                 @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foods = foodService.getResturantsFood(resturantId, vegetarian, nonveg, seasonal, food_category);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }


}
