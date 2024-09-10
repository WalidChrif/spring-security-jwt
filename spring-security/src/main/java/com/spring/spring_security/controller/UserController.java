package com.spring.spring_security.controller;

import com.spring.spring_security.entity.TheUser;
import com.spring.spring_security.services.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/login")
    public String login(@RequestBody TheUser user){
        return userService.login(user);
    }
    @PostMapping("/register")
    public String register(@RequestBody TheUser user) {
        return userService.addUser(user);
    }

//    @PostMapping("/logout")
//    public void logout(HttpServletRequest request, HttpServletResponse response){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        authentication.setAuthenticated(false);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
//        securityContextLogoutHandler.logout(request, response, authentication);
//    }


}
