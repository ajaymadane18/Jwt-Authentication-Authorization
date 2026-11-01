package com.JwtDemo.Security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.JwtDemo.Models.Register;
import com.JwtDemo.Repo.RegisterRepository;



@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    @Autowired
    private RegisterRepository registerRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Register user = registerRepository.findByEmail(email)
                .orElseThrow(() ->
                    new UsernameNotFoundException("User not found")
                );

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}