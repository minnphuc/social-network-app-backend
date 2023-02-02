package com.minnphuc.socialapp.controller;

import com.minnphuc.socialapp.auth.jwt.UserNotFoundException;
import com.minnphuc.socialapp.model.Post;
import com.minnphuc.socialapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders= "*")
@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/getAll")
    public List<Post> getAllPost() {
        return postService.getAllPost();
    };

    @PostMapping("/upload")
    public String uploadImg(@RequestParam("file") MultipartFile file) throws Exception {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getContentType());
        System.out.println(file.getName());
        System.out.println(file.getSize());

        String Path_Directory = "C:\\Users\\HP\\.vscode\\PracticeReactProject\\SocialApp\\public\\assets";
        Files.copy(file.getInputStream(), Paths.get(Path_Directory+ File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

        return "Upload image successfully!";
    }

    @PostMapping("/add")
    public Post signup(@RequestBody Post post) {
        return postService.savePost(post);
    }

    @PutMapping("/like/{id}")
    public Post updatePostLike(@PathVariable(value = "id") long userId,
                                 @RequestBody Post postDetails) throws UserNotFoundException {

        Post post = postService.findById(userId);

        post.setLikedBy(postDetails.getLikedBy());

        Post updatedPost = postService.savePost(post);

        return updatedPost;
    }
}
