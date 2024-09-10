package com.spring.spring_security.services;

import com.spring.spring_security.entity.TheUser;
import com.spring.spring_security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTService jwtService;

    public String login(TheUser user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user);
        }
        else return "logging failed";
    }

    public String addUser(TheUser user) throws UsernameNotFoundException {
        TheUser existingUser = userRepo.findUserByUsername(user.getUsername());
        if (existingUser != null) throw new RuntimeException("User already exists");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return jwtService.generateToken(user);
    }
}
