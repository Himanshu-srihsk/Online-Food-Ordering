package com.fun.service;

import com.fun.dto.ResturantDto;
import com.fun.model.Address;
import com.fun.model.Resturant;
import com.fun.model.User;
import com.fun.repository.AddressRepository;
import com.fun.repository.ResturantRepository;
import com.fun.repository.UserRepository;
import com.fun.request.CreateResturantRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ResturantServiceImpl implements ResturantService{
    @Autowired
    private ResturantRepository resturantRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Resturant createResturant(CreateResturantRequest req, User user) {
        Address address = addressRepository.save(req.getAddress());
        Resturant resturant = new Resturant();
        resturant.setAddress(address);
        resturant.setContactInformation(req.getContactInformation());
        resturant.setCuisineType(req.getCuisineType());
        resturant.setDescription(req.getDescription());
        resturant.setImages(req.getImages());
        resturant.setName(req.getName());
        resturant.setOpeningHours(req.getOpeningHours());
        resturant.setRegistrationDate(LocalDateTime.now());
        resturant.setOwner(user);
        return resturantRepository.save(resturant);
    }

    @Override
    public Resturant updateResturant(Long resturantId, CreateResturantRequest updatedResturant) throws Exception {
        Resturant existingResturant = resturantRepository.findById(resturantId).orElseThrow(() -> new Exception("Resturant not found"));
        existingResturant.setCuisineType(updatedResturant.getCuisineType());
        existingResturant.setDescription(updatedResturant.getDescription());
        existingResturant.setName(updatedResturant.getName());
        return resturantRepository.save(existingResturant);
    }

    @Override
    public void deleteResturant(Long resturantId) throws Exception {
        Resturant resturant = findResturantById(resturantId);
        resturantRepository.delete(resturant);
    }

    @Override
    public List<Resturant> getAllResturants() {
        return resturantRepository.findAll();
    }

    @Override
    public List<Resturant> searchResturants(String keyword) {
        return resturantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Resturant findResturantById(Long resturantId) throws Exception {
        Optional<Resturant> opt = resturantRepository.findById(resturantId);
        if(opt.isEmpty()){
            throw new Exception("Resturant not found with id"+resturantId);
        }
        return opt.get();
    }

    @Override
    public Resturant getResturantByUserId(Long userId) throws Exception {
        Resturant resturant = resturantRepository.findByOwnerId(userId);
        if(resturant== null){
            throw new Exception("Resturant not found with uid:"+userId);
        }
        return resturant;
    }

    @Override
    public ResturantDto addToFavourites(Long resturantId, User user) throws Exception {
        Resturant resturant = findResturantById(resturantId);
        ResturantDto resturantDto = new ResturantDto();
        resturantDto.setDescription(resturant.getDescription());
        resturantDto.setTitle(resturant.getName());
        resturantDto.setImages(resturant.getImages());
        resturantDto.setId(resturantId);
        if(user.getFavourites().contains(resturantDto)){
            user.getFavourites().remove(resturantDto);
        }else{
            user.getFavourites().add(resturantDto);
        }
        userRepository.save(user);
        return resturantDto;
    }

    @Override
    public Resturant updateResturantStatus(Long resturantId) throws Exception {
        Resturant resturant = findResturantById(resturantId);
        resturant.setOpen(!resturant.isOpen());
        return resturantRepository.save(resturant);
    }
}
