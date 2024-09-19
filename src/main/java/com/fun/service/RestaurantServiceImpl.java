package com.fun.service;

import com.fun.dto.RestaurantDto;
import com.fun.model.Address;
import com.fun.model.Restaurant;
import com.fun.model.User;
import com.fun.repository.AddressRepository;
import com.fun.repository.RestaurantRepository;
import com.fun.repository.UserRepository;
import com.fun.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createResturant(CreateRestaurantRequest req, User user) {
        Address address = addressRepository.save(req.getAddress());
        Restaurant resturant = new Restaurant();
        resturant.setAddress(address);
        resturant.setContactInformation(req.getContactInformation());
        resturant.setCuisineType(req.getCuisineType());
        resturant.setDescription(req.getDescription());
        resturant.setImages(req.getImages());
        resturant.setName(req.getName());
        resturant.setOpeningHours(req.getOpeningHours());
        resturant.setRegistrationDate(LocalDateTime.now());
        resturant.setOwner(user);
        return restaurantRepository.save(resturant);
    }

    @Override
    public Restaurant updateResturant(Long resturantId, CreateRestaurantRequest updatedResturant) throws Exception {
        Restaurant existingResturant = restaurantRepository.findById(resturantId).orElseThrow(() -> new Exception("Resturant not found"));
        existingResturant.setCuisineType(updatedResturant.getCuisineType());
        existingResturant.setDescription(updatedResturant.getDescription());
        existingResturant.setName(updatedResturant.getName());
        return restaurantRepository.save(existingResturant);
    }

    @Override
    public void deleteResturant(Long resturantId) throws Exception {
        Restaurant resturant = findResturantById(resturantId);
        restaurantRepository.delete(resturant);
    }

    @Override
    public List<Restaurant> getAllResturants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchResturants(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findResturantById(Long resturantId) throws Exception {
        Optional<Restaurant> opt = restaurantRepository.findById(resturantId);
        if(opt.isEmpty()){
            throw new Exception("Resturant not found with id"+resturantId);
        }
        return opt.get();
    }

    @Override
    public Restaurant getResturantByUserId(Long userId) throws Exception {
        Restaurant resturant = restaurantRepository.findByOwnerId(userId);
        if(resturant== null){
            throw new Exception("Resturant not found with uid:"+userId);
        }
        return resturant;
    }

    @Override
    public RestaurantDto addToFavourites(Long resturantId, User user) throws Exception {
        Restaurant resturant = findResturantById(resturantId);
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setDescription(resturant.getDescription());
        restaurantDto.setTitle(resturant.getName());
        restaurantDto.setImages(resturant.getImages());
        restaurantDto.setId(resturantId);
          Boolean isFavorited = false;
          List<RestaurantDto> favorites = user.getFavourites();
          for(RestaurantDto fav : favorites){
              if(fav.getId().equals(resturantId)){
                  isFavorited = true;
                  break;
              }
          }
          if(isFavorited){
              favorites.removeIf(favourite -> favourite.getId().equals(resturantId));
          }else{
              favorites.add(restaurantDto);
          }
        userRepository.save(user);
        return restaurantDto;
    }

    @Override
    public Restaurant updateResturantStatus(Long resturantId) throws Exception {
        Restaurant resturant = findResturantById(resturantId);
        resturant.setOpen(!resturant.isOpen());
        return restaurantRepository.save(resturant);
    }
}
