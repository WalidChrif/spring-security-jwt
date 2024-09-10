package com.spring.spring_security.services;

import com.spring.spring_security.entity.TheUser;
import com.spring.spring_security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TheUser theUser = userRepo.findUserByUsername(username);
        if (theUser == null) throw new RuntimeException("Username not found exception");
        return theUser;
    }


}
