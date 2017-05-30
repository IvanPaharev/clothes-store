package com.netcracker.store.web.controller;

import com.netcracker.store.logic.service.UserService;
import com.netcracker.store.persistence.entity.Dress;
import com.netcracker.store.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Set;

/**
 * Created by A-one on 28.04.2017.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET)
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Boolean> addUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Set<User>> getAllUsers() {
        Set<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/details", method = RequestMethod.POST)
    public ResponseEntity<User> getUserDetails(@RequestBody String email) {
        User user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
