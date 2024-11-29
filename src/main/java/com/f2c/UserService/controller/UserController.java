package com.f2c.UserService.controller;

import com.f2c.UserService.model.User;
import com.f2c.UserService.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(HttpServletRequest request, Long id) {
        return userService.getUser(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(HttpServletRequest request, Long id, User user) {
        return userService.getUser(id).map(u -> {
            u.setUserName(user.getUserName());
            u.setEmail(user.getEmail());
            u.setPassword(user.getPassword());
            u.setMobileNumber(user.getMobileNumber());
            u.setRole(user.getRole());
            return ResponseEntity.ok(userService.saveUser(u));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(HttpServletRequest request, User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(HttpServletRequest request, Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
