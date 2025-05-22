package com.trump.panama.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trump.panama.entity.User;
import com.trump.panama.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(String username, String password, String email) {
        // Create a new User object
        User user = new User();
        user.setUsername(username);

        // Encode the password before saving it
        user.setPassword(passwordEncoder.encode(password));
        
        user.setEmail(email);
        
        // Save the user to the database
        userRepository.save(user);
    }
}