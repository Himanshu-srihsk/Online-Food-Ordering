package com.fun.controller;

import com.fun.dto.ResturantDto;
import com.fun.model.Resturant;
import com.fun.model.User;
import com.fun.request.CreateResturantRequest;
import com.fun.service.ResturantService;
import com.fun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resturants")
public class ResturantController {
    @Autowired
    private ResturantService resturantService;
    @Autowired
    private UserService userService;
    @GetMapping("/search")
    public ResponseEntity<List<Resturant>> searchResturant(@RequestParam String keyword,
                                                @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Resturant> resturant = resturantService.searchResturants(keyword);
        return new ResponseEntity<>(resturant, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Resturant>> getAllResturant( @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Resturant> resturant = resturantService.getAllResturants();
        return new ResponseEntity<>(resturant, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resturant> findResturantById( @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        System.out.println("id is"+id);
        Resturant resturant= resturantService.findResturantById(id);
        return new ResponseEntity<>(resturant, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favourites")
    public ResponseEntity<ResturantDto> addToFavourites( @PathVariable Long id,
                                                        @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        ResturantDto resturant= resturantService.addToFavourites(id, user);

        return new ResponseEntity<>(resturant, HttpStatus.OK);
    }
}
