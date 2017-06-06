package com.netcracker.store.logic.test;

import com.netcracker.store.logic.config.ServiceConfig;
import com.netcracker.store.persistence.entity.User;
import com.netcracker.store.logic.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

/**
 * Created by A-one on 23.04.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
@EnableConfigurationProperties
@ContextConfiguration(classes = ServiceConfig.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetUserByEmail() {
        User user = userService.getUserByEmail("admin@test.com");
        assertNotNull(user);
    }

    @Test
    public void testAddAndDeleteUser() {
        User user = new User("t", "t", "t", "t", "t", "t");
        userService.add(user);
        assertNotNull(user.getId());
        userService.delete(user);
        assertNull(userService.getUserByEmail("t"));
    }

    @Test
    public void testGetAllUsers() {
        assertNotNull(userService.getAll());
    }
}
