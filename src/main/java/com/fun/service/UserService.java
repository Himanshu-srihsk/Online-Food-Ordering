package com.fun.service;

import com.fun.exception.UserException;
import com.fun.model.User;

public interface UserService {
    public User findUserByJwtToken(String jwt) throws UserException;
    public User findUserByEmail(String email) throws UserException;
}
