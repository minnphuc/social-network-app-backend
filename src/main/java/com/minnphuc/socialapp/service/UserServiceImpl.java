package com.minnphuc.socialapp.service;

import com.minnphuc.socialapp.auth.jwt.UserNotFoundException;
import com.minnphuc.socialapp.model.User;
import com.minnphuc.socialapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repo;

    @Override
    public User saveUser(User user) {
        return repo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return repo.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public User findById(long id) throws UserNotFoundException {
        return repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
