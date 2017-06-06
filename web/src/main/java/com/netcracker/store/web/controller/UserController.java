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

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;

/**
 * Created by A-one on 28.04.2017.
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User, Integer> {

    private final UserService userService;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        super(userService);
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> add(@Valid @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new ResponseEntity<>(userService.add(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/security", method = RequestMethod.GET)
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping(value = "/details", method = RequestMethod.POST)
    public ResponseEntity<User> getUserDetails(@RequestBody String email) {
        User user = userService.getUserByEmail(email);
        user.setPassword("");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
