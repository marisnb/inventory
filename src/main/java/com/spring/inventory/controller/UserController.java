package com.spring.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.inventory.model.User;
import com.spring.inventory.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

//    @PostMapping(value = "/authenticate")
//    public ResponseEntity<Object> authenticate(@RequestBody User user){
//
//        return  new ResponseEntity<>(userService.authenticate(user),HttpStatus.OK);
//
//    }

    @PostMapping(value="/create")
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getalluser(){
        return  new ResponseEntity<>("sankar",HttpStatus.OK);
    }
}
