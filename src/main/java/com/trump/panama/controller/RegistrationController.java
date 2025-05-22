package com.trump.panama.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trump.panama.service.UserService;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // Return the registration form view
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password,@RequestParam String email) {
        // Create a new user with the encoded password
        userService.createUser(username, password, email);

        // Redirect to the login page
        return "redirect:/login";
    }
}
