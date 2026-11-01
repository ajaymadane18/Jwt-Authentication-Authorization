package com.JwtDemo.Controllers;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String getAdminDashboard() {

        return "Welcome to Admin Dashboard";
    }

    @GetMapping("/users")
    public String getAllUsers() {

        return "Only Admin can access all users";
    }
}
