package com.netcracker.store.logic.service;

import com.netcracker.store.persistence.entity.User;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by A-one on 23.04.2017.
 */
@Service
public interface UserService {
    User getUserByEmail(String email);
    void addUser(User user);
    Set<User> getAllUsers();
    void deleteUser(User user);
}
