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
    public void addUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Set<User>> getAllUsers() {
        Set<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

        /*    @RequestMapping(value = "/dress/", method = RequestMethod.POST)
    public ResponseEntity<Void> createDress(@RequestBody Dress dress, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + dress.getCategory().getName());

        if (dress.getId() != null && dressService.getDressById(dress.getId()) != null) {
            System.out.println("A User with name " + dress.getCategory().getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        System.out.println(dress.toString());
        dressService.add(dress);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/dress/{id}").buildAndExpand(dress.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }*/
}
