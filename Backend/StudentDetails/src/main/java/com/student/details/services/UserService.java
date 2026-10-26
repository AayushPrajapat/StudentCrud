package com.student.details.services;

import com.student.details.entities.LoginRequest;
import com.student.details.entities.User;
import com.student.details.exceptions.EmailRegisteredException;
import com.student.details.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //    SignUp
    /*
    isPresent() checks whether an Optional contains a value or not.
    It returns:
    true → value exists
    false → value does not exist
    */
    public User signUpUser(User user) {
        String normalizedEmail = normalizeEmail(user.getEmail());
        user.setEmail(normalizedEmail);
        user.setName(user.getName());

        if (userRepository.findByEmail(normalizedEmail).isPresent()) {
            throw new EmailRegisteredException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String loginUser(LoginRequest loginRequest) {
        String normalizedEmail = normalizeEmail(loginRequest.getEmail());

        return userRepository.findByEmail(normalizedEmail)
                .map(user -> passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())
                        ? "User Login SuccessFully..!!"
                        : "Wrong Password..!!")
                .orElse("Email Not Found..!!");
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    private String normalizeEmail(String email) {
        return email == null ? null : email.trim().toLowerCase(Locale.ROOT);
    }
}
