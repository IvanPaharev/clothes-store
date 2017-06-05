package com.netcracker.store.persistence.test;

import com.netcracker.store.persistence.dao.BaseDao;
import com.netcracker.store.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by A-one on 10.04.2017.
 */
public class MySqlUserOrderDaoTest extends BaseDaoGenericTest{

    @Autowired
    @Resource(name = "mySqlUserOrderDao")
    private BaseDao<UserOrder, Integer> userOrderDao;

    @Autowired
    @Resource(name = "mySqlUserDao")
    private BaseDao<User, Integer> userDao;

    @Autowired
    @Resource(name = "mySqlOrderStatusDao")
    private BaseDao<OrderStatus, Integer> orderStatusDao;

    @Autowired
    @Resource(name = "mySqlOrderDetailDao")
    private BaseDao<OrderDetail, OrderDetailPK> orderDetailDao;

    @Override
    public void testUpdate() {
        Date newName = new Date(System.currentTimeMillis());
        UserOrder userOrder = (UserOrder) getDao().get(testId);
        userOrder.setDateCreated(newName);
        getDao().update(userOrder);
        assertEquals(newName, userOrder.getDateCreated());
    }

    @Override
    protected BaseDao getDao() {
        return userOrderDao;
    }

    @Override
    protected BaseEntity getEntity() {
        return new UserOrder(new Date(System.currentTimeMillis()),
                orderStatusDao.getAll().get(0),
                userDao.getAll().get(0),
                new HashSet<>(orderDetailDao.getAll()));
    }

    @Override
    protected Collection<BaseEntity> getCollection() {
        return Arrays.asList(new UserOrder(new Date(System.currentTimeMillis()),
                        orderStatusDao.getAll().get(0),
                        userDao.getAll().get(0),
                        new HashSet<>(orderDetailDao.getAll())),
                new UserOrder(new Date(System.currentTimeMillis()),
                        orderStatusDao.getAll().get(1),
                        userDao.getAll().get(1),
                        new HashSet<>(orderDetailDao.getAll())));
    }
}
