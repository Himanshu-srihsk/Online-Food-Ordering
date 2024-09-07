package com.fun.service;

import com.fun.dto.ResturantDto;
import com.fun.model.Resturant;
import com.fun.model.User;
import com.fun.request.CreateResturantRequest;

import java.util.List;

public interface ResturantService {
    public Resturant createResturant(CreateResturantRequest req, User user);
    public Resturant updateResturant(Long resturantId, CreateResturantRequest updatedResturant) throws Exception;
    public void deleteResturant(Long resturantId) throws Exception;
    public List<Resturant> getAllResturants();
    public List<Resturant> searchResturants(String keyword);
    public Resturant findResturantById(Long resturantId) throws Exception;
    public Resturant getResturantByUserId(Long userId) throws Exception;
    public ResturantDto addToFavourites(Long resturantId, User user) throws Exception;
    public Resturant updateResturantStatus(Long resturantId) throws Exception;
}
