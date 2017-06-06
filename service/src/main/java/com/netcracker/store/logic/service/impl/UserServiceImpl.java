package com.netcracker.store.logic.service.impl;

import com.netcracker.store.logic.service.RoleService;
import com.netcracker.store.persistence.dao.UserDao;
import com.netcracker.store.persistence.entity.User;
import com.netcracker.store.logic.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by A-one on 23.04.2017.
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {
    private final UserDao userDao;
    private final RoleService roleService;

    public UserServiceImpl(UserDao userDao, RoleService roleService) {
        super(userDao);
        this.userDao = userDao;
        this.roleService = roleService;
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public User update(User user) {
        user.setPassword(userDao.get(user.getId()).getPassword());
        return userDao.update(user);
    }

    @Override
    public User add(User user) {
        if (userDao.getUserByEmail(user.getEmail()) == null) {
            user = userDao.add(user);
            user.setRoles(new ArrayList<>(Collections.singletonList(roleService.get(1))));
        }
        return user;
    }

}
