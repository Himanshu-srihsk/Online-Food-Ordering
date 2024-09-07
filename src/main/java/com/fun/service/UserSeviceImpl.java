package com.fun.service;

import com.fun.config.JwtProvider;
import com.fun.exception.UserException;
import com.fun.model.User;
import com.fun.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSeviceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public User findUserByJwtToken(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findUserByEmail(email);
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws UserException {
        User user = userRepository.findUserByEmail(email);
        if(user == null){
            throw new UserException("User not found with email: " + email);
        }
        return user;
    }
}
