package com.minnphuc.socialapp.service;

import com.minnphuc.socialapp.auth.jwt.UserNotFoundException;
import com.minnphuc.socialapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User saveUser(User user);
    public List<User> getAllUser();
    public User findByEmail(String email);
    public User findById(long id) throws UserNotFoundException;
}
