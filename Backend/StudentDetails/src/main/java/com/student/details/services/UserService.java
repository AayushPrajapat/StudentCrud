package com.student.details.services;

import com.student.details.entities.LoginRequest;
import com.student.details.entities.User;
import com.student.details.exceptions.EmailRegisteredException;
import com.student.details.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    //    SignUp
    /*
    isPresent() checks whether an Optional contains a value or not.
    It returns:
    true → value exists
    false → value does not exist
    */
    public User signUpUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailRegisteredException();
        }
        return userRepository.save(user);
    }

    public String loginUser(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isPresent()) {
            User existingUser = user.get();
            if (existingUser.getPassword().equals(loginRequest.getPassword())) {
                return "User Login SuccessFully..!!";
            }
            return "Wrong Password..!!";
        }
        return "Email Not Found..!!";
    }
}
