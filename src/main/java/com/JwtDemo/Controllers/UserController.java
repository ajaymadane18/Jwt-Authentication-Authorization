package com.JwtDemo.Controllers;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/profile")
    public String getProfile(Authentication authentication) {

        return "Welcome " + authentication.getName();
    }

    @GetMapping("/dashboard")
    public String getDashboard() {

        return "Welcome to User Dashboard";
    }
}