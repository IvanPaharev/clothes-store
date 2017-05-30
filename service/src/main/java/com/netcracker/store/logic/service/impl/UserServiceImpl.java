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
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public boolean addUser(User user) {
        boolean isEmailBusy = true;
        if (userDao.getUserByEmail(user.getEmail()) == null) {
            userDao.add(user);
            if (user.getId() != null) {
                isEmailBusy = false;
            }
        }
        return isEmailBusy;
    }

    @Override
    public Set<User> getAllUsers() {
        return new HashSet<>(userDao.getAll());
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user.getId());
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }
}
