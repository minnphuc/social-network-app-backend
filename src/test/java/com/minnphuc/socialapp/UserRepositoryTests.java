package com.minnphuc.socialapp;

import com.minnphuc.socialapp.model.User;
import com.minnphuc.socialapp.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("test1@test.com");
        user.setPassword("$2a$10$cUarPgWRknSTVrSh8dCJTOYsU9EmG/2q.xwdqgSilHe30n905zSnG");
        user.setName("Nguyen Van A");
        user.setLocation("HCM");
        user.setHometown("Quang Nam");
        user.setRelationship(0);
        user.setBiography("Messi > CR7");

        User savedUser = repo.save(user);
    }

    @Test
    public void testFindUserByEmail() {
        String email = "test@test.com";

        User user = repo.findByEmail(email);

        System.out.println(user.getName());
    }

    @Test
    public void generateJwtToken() {
        String jwt =  Jwts.builder()
                .setSubject("Phuc")
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 86400000))
                .signWith(SignatureAlgorithm.HS512, "minnphucSecretKey")
                .compact();

        System.out.println(jwt);
    }

    @Test
    public void encryptPassword() {
        String password = "testtest";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println(encoder.encode(password));
    }
}
