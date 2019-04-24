package com.spring.inventory.service;

import com.spring.inventory.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User createUser(User user);
    //public User authenticate(User user);
}
