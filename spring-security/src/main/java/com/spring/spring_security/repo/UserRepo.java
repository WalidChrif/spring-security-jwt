package com.spring.spring_security.repo;

import com.spring.spring_security.entity.TheUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<TheUser, Integer> {


    TheUser findUserByUsername(String username);
}
