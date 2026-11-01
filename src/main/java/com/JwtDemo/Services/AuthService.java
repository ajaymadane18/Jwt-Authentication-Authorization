package com.JwtDemo.Services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.JwtDemo.DTO.AuthResponse;
import com.JwtDemo.DTO.LoginRequest;
import com.JwtDemo.DTO.RegisterRequest;
import com.JwtDemo.Models.Register;
import com.JwtDemo.Models.Role;
import com.JwtDemo.Repo.RegisterRepository;
import com.JwtDemo.Security.JwtService;



@Service
public class AuthService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public String register(RegisterRequest request) {

        if (registerRepository.existsByEmail(request.getEmail())) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email already registered"
            );
        }

        Register user = new Register();

        user.setFullname(request.getFullname());
        user.setEmail(request.getEmail());

        // Encrypt password
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        // Every public registration gets USER role
        user.setRole(Role.USER);

        registerRepository.save(user);

        return "Registration successful";
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Register user = registerRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User not found"
                        )
                );

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new AuthResponse(
                token,
                user.getEmail(),
                user.getRole().name()
        );
    }
}