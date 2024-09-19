package com.fun.service;

import com.fun.dto.RestaurantDto;
import com.fun.model.Restaurant;
import com.fun.model.User;
import com.fun.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {
    public Restaurant createResturant(CreateRestaurantRequest req, User user);
    public Restaurant updateResturant(Long resturantId, CreateRestaurantRequest updatedResturant) throws Exception;
    public void deleteResturant(Long resturantId) throws Exception;
    public List<Restaurant> getAllResturants();
    public List<Restaurant> searchResturants(String keyword);
    public Restaurant findResturantById(Long resturantId) throws Exception;
    public Restaurant getResturantByUserId(Long userId) throws Exception;
    public RestaurantDto addToFavourites(Long resturantId, User user) throws Exception;
    public Restaurant updateResturantStatus(Long resturantId) throws Exception;
}
