package com.spring.inventory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.inventory.model.User;
import com.spring.inventory.repo.UserRepo;
import com.spring.inventory.service.UserService;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setCredentialExpired(false);
        user.setAccountLocked(false);
        user.setAccountExpired(false);
        user.setFailedPasswordAttempts(0);
        return userRepo.save(user);
    }
//    @Override
//    public User authenticate(User user) {
//        return userRepo.findByUsername(user.getUsername());
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }
}
