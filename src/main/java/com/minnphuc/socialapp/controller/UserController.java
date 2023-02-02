package com.minnphuc.socialapp.controller;

import com.minnphuc.socialapp.auth.jwt.JwtUtils;
import com.minnphuc.socialapp.auth.jwt.UserNotFoundException;
import com.minnphuc.socialapp.payload.JwtResponse;
import com.minnphuc.socialapp.payload.LoginRequest;
import com.minnphuc.socialapp.payload.MsgResponse;
import com.minnphuc.socialapp.service.UserService;
import com.minnphuc.socialapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders= "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Value("${minnphuc.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        User existUser = userService.findByEmail(user.getEmail());

        if (existUser != null)
            return ResponseEntity.badRequest().body(new MsgResponse("Error: Email is already in use!"));

        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        User addedUser = userService.saveUser(user);
        return ResponseEntity.ok(new MsgResponse("User registered successfully!"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request) {
        String inputEmail = request.getEmail();
        String inputPassword = request.getPassword();
        User existUser = userService.findByEmail(inputEmail);

        if (existUser == null) return ResponseEntity.badRequest().body(new MsgResponse("Error: Account does not exist!"));

        if (encoder.matches(inputPassword, existUser.getPassword())) {
            String jwt = jwtUtils.generateJwtToken(existUser.getEmail());

            return ResponseEntity.ok(new JwtResponse(jwt, existUser.getId(), jwtExpirationMs ));
        } else {
            return ResponseEntity.badRequest().body(new MsgResponse("Error: Your password is not correct!"));
        }

    }

    @PostMapping("/upload")
    public String uploadImg(@RequestParam("file") MultipartFile file) throws Exception {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getContentType());
        System.out.println(file.getName());
        System.out.println(file.getSize());

        String Path_Directory = "C:\\Users\\HP\\.vscode\\PracticeReactProject\\SocialApp\\public\\assets\\avatars";
        Files.copy(file.getInputStream(), Paths.get(Path_Directory+File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

        return "Upload image successfully!";
    }

    @GetMapping("/getAll")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable(value = "id") long id) throws UserNotFoundException {return userService.findById(id);}

    @PutMapping("/{id}")
    public User updateUser(@PathVariable(value = "id") long userId,
                            @RequestBody User userDetails) throws UserNotFoundException {

        User user = userService.findById(userId);

        user.setLocation(userDetails.getLocation());
        user.setHometown(userDetails.getHometown());
        user.setBiography(userDetails.getBiography());
        user.setRelationship(userDetails.getRelationship());

        User updatedUser = userService.saveUser(user);

        return updatedUser;
    }

    @PutMapping("/avatar/{id}")
    public User updateUserAvatar(@PathVariable(value = "id") long userId,
                           @RequestBody User userDetails) throws UserNotFoundException {

        User user = userService.findById(userId);

        user.setAvatar(userDetails.getAvatar());

        User updatedUser = userService.saveUser(user);

        return updatedUser;
    }
}
