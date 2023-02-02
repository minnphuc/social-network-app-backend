package com.minnphuc.socialapp.service;

import com.minnphuc.socialapp.auth.jwt.UserNotFoundException;
import com.minnphuc.socialapp.model.Post;
import com.minnphuc.socialapp.model.User;
import com.minnphuc.socialapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository repo;

    @Override
    public Post savePost(Post post) {
        return repo.save(post);
    }

    @Override
    public List<Post> getAllPost() {
        return repo.findAll();
    }

    @Override
    public Post findById(long id) throws UserNotFoundException {
        return repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
