package com.fun.controller;

import com.fun.exception.UserException;
import com.fun.model.User;
import com.fun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/profile")
    private ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserByJwtToken(jwt);
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }
}
