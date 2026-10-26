package com.student.details.controllers;

import com.student.details.entities.LoginRequest;
import com.student.details.entities.User;
import com.student.details.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller ke URLs ka base path define karna.
// @RequestMapping tells Spring which URL should be handled by a controller or controller method.
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@Valid @RequestBody User user) {
        User savedUser = userService.signUpUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest request) {
        String response = userService.loginUser(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
