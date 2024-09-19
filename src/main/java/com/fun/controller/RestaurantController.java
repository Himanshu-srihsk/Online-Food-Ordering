package com.fun.controller;

import com.fun.dto.RestaurantDto;
import com.fun.model.Restaurant;
import com.fun.model.User;
import com.fun.service.RestaurantService;
import com.fun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resturants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;
    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchResturant(@RequestParam String keyword,
                                                            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> resturant = restaurantService.searchResturants(keyword);
        return new ResponseEntity<>(resturant, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllResturant(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> resturant = restaurantService.getAllResturants();
        return new ResponseEntity<>(resturant, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findResturantById(@PathVariable Long id,
                                                        @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        System.out.println("id is"+id);
        Restaurant resturant= restaurantService.findResturantById(id);
        return new ResponseEntity<>(resturant, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favourites")
    public ResponseEntity<RestaurantDto> addToFavourites(@PathVariable Long id,
                                                         @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        RestaurantDto resturant= restaurantService.addToFavourites(id, user);

        return new ResponseEntity<>(resturant, HttpStatus.OK);
    }
}
