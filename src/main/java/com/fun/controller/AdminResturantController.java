package com.fun.controller;

import com.fun.model.Resturant;
import com.fun.model.User;
import com.fun.request.CreateResturantRequest;
import com.fun.response.MessageResponse;
import com.fun.service.ResturantService;
import com.fun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/restaurants")
public class AdminResturantController {
    @Autowired
    private ResturantService resturantService;
    @Autowired
    private UserService userService;
    @PostMapping()
    public ResponseEntity<Resturant> createResturant(@RequestBody CreateResturantRequest req,
                                                     @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Resturant resturant = resturantService.createResturant(req,user);
        return new ResponseEntity<>(resturant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resturant> updateResturant(@RequestBody CreateResturantRequest req,
                                                     @RequestHeader("Authorization") String jwt,
                                                     @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Resturant resturant = resturantService.updateResturant(id,req);
        return new ResponseEntity<>(resturant, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteResturant(@RequestBody CreateResturantRequest req,
                                                     @RequestHeader("Authorization") String jwt,
                                                     @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        resturantService.deleteResturant(id);
        MessageResponse response = new MessageResponse();
        response.setMessage("Resturant deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Resturant> updateResturantStatus(@RequestBody CreateResturantRequest req,
                                                           @RequestHeader("Authorization") String jwt,
                                                           @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Resturant resturant= resturantService.updateResturantStatus(id);

        return new ResponseEntity<>(resturant, HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<Resturant> findResturantByUserId(@RequestBody CreateResturantRequest req,
                                                           @RequestHeader("Authorization") String jwt
                                                           ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Resturant resturant= resturantService.getResturantByUserId(user.getId());

        return new ResponseEntity<>(resturant, HttpStatus.OK);
    }
}
