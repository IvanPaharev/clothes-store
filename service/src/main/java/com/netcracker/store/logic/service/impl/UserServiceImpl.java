package com.netcracker.store.logic.service.impl;

import com.netcracker.store.persistence.dao.UserDao;
import com.netcracker.store.persistence.entity.User;
import com.netcracker.store.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by A-one on 23.04.2017.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    @Resource(name = "mySqlUserDao")
    private UserDao userDao;

    @Override
    public User getUserByEmail(String email) {
        List<User> users = userDao.getAll();
        User user = null;
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                user = u;
            }
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        userDao.add(user);
    }

    @Override
    public Set<User> getAllUsers() {
        return new HashSet<>(userDao.getAll());
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user.getId());
    }
}
