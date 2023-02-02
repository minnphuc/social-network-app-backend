package com.minnphuc.socialapp.service;

import com.minnphuc.socialapp.auth.jwt.UserNotFoundException;
import com.minnphuc.socialapp.model.Post;

import java.util.List;

public interface PostService {
    public Post savePost(Post post);
    public List<Post> getAllPost();
    public Post findById(long id) throws UserNotFoundException;
}
