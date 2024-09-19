package com.fun.controller;

import com.fun.model.Restaurant;
import com.fun.model.User;
import com.fun.request.CreateRestaurantRequest;
import com.fun.response.MessageResponse;
import com.fun.service.RestaurantService;
import com.fun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/resturants")
public class AdminRestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;
    @PostMapping()
    public ResponseEntity<Restaurant> createResturant(@RequestBody CreateRestaurantRequest req,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant resturant = restaurantService.createResturant(req,user);
        return new ResponseEntity<>(resturant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateResturant(@RequestBody CreateRestaurantRequest req,
                                                      @RequestHeader("Authorization") String jwt,
                                                      @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant resturant = restaurantService.updateResturant(id,req);
        return new ResponseEntity<>(resturant, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteResturant(
                                                     @RequestHeader("Authorization") String jwt,
                                                     @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        restaurantService.deleteResturant(id);
        MessageResponse response = new MessageResponse();
        response.setMessage("Resturant deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateResturantStatus(
                                                           @RequestHeader("Authorization") String jwt,
                                                           @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant resturant= restaurantService.updateResturantStatus(id);

        return new ResponseEntity<>(resturant, HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<?> findResturantByUserId(
                                                           @RequestHeader("Authorization") String jwt
                                                           ) throws Exception {
       // System.out.println("jwt token in Backened"+ jwt);
        User user = userService.findUserByJwtToken(jwt);
        Restaurant resturant= restaurantService.getResturantByUserId(user.getId());
        if (resturant == null) {
            return new ResponseEntity<>("Restaurant not found for this user", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resturant, HttpStatus.OK);
    }
}
