package com.student.details.controllers;

import com.student.details.entities.LoginRequest;
import com.student.details.entities.User;
import com.student.details.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

// Controller ke URLs ka base path define karna.
// @RequestMapping tells Spring which URL should be handled by a controller or controller method.
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@Validated @RequestBody User user) {
        User savedUser = this.userService.signUpUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Validated @RequestBody LoginRequest request){
        System.out.println("Login Request");
        String s = this.userService.loginUser(request);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }

}
