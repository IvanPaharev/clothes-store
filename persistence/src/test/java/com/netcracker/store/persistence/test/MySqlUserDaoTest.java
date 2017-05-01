package com.netcracker.store.persistence.test;

import com.netcracker.store.persistence.dao.BaseDao;
import com.netcracker.store.persistence.entity.BaseEntity;
import com.netcracker.store.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

/**
 * Created by A-one on 10.04.2017.
 */
public class MySqlUserDaoTest extends BaseDaoGenericTest{

    @Autowired
    @Resource(name = "mySqlUserDao")
    private BaseDao<User, Integer> userDao;

    @Override
    public void testUpdate() {
        String newName = "newCategory";
        User user = (User) getDao().get(testId);
        user.setFirstname(newName);
        getDao().update(user);
        assertEquals(newName, user.getFirstname());
    }

    @Override
    protected BaseDao getDao() {
        return userDao;
    }

    @Override
    protected BaseEntity getEntity() {
        return new User("t", "t", "t", "t", "t", "t");
    }
}
