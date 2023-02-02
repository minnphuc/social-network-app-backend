package com.minnphuc.socialapp.auth.jwt;

public class UserNotFoundException extends Exception {
    private long user_id;
    public UserNotFoundException(long user_id) {
        super(String.format("User not found with id : '%s'", user_id));
    }
}
