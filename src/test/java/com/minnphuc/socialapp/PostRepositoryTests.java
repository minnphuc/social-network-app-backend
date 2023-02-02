package com.minnphuc.socialapp;

import com.minnphuc.socialapp.model.Post;
import com.minnphuc.socialapp.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class PostRepositoryTests {
    @Autowired
    PostRepository repo;

    @Test
    public void testCreatePost() {
        Post post = new Post();

        post.setDescription("Night time");
        post.setPhoto("/assets/img6.jpg");
        post.setPostedAt("Sat Jan 1 2023 00:00:00 GMT+0700 (Indochina Time)");
        post.setUserId(4);
        post.setCommentCount(3);

        repo.save(post);
    }
}
