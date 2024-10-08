package com.fun.controller;

import com.fun.model.Food;
import com.fun.model.Restaurant;
import com.fun.model.User;
import com.fun.request.CreateFoodRequest;
import com.fun.response.MessageResponse;
import com.fun.service.FoodService;
import com.fun.service.RestaurantService;
import com.fun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
//        System.out.println("jwt is " + jwt);
       // System.out.println("req resturant Id is " + req.getResturantId());
        User user = userService.findUserByJwtToken(jwt);
//        Resturant resturant = resturantService.findResturantById(req.getResturantId());
       // System.out.println("req user Id is " + user.getId());
        Restaurant resturant = restaurantService.getResturantByUserId(user.getId());
        Food food = foodService.createFood(req,req.getCategory(),resturant);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);
        MessageResponse res= new MessageResponse();
        res.setMessage("Food deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailabilitySataus(@PathVariable Long id,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.updateAvailability(id);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

}
