package com.netcracker.store.persistence.test;

import com.netcracker.store.persistence.config.DataConfig;
import com.netcracker.store.persistence.dao.BaseDao;
import com.netcracker.store.persistence.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by A-one on 10.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
@Rollback
@Transactional
public class MySqlOrderDetailDaoTest{

    @Autowired
    @Resource(name = "mySqlOrderDetailDao")
    private BaseDao<OrderDetail, OrderDetailPK> orderDetailDao;

    @Autowired
    @Resource(name = "mySqlDressDao")
    private BaseDao<Dress, Integer> dressDao;

    @Autowired
    @Resource(name = "mySqlUserOrderDao")
    private BaseDao<UserOrder, Integer> userOrderDao;

    @Autowired
    @Resource(name = "mySqlUserDao")
    private BaseDao<User, Integer> userDao;

    @Autowired
    @Resource(name = "mySqlOrderStatusDao")
    private BaseDao<OrderStatus, Integer> orderStatusDao;

    @Test
    public void testUpdate() {
        Integer newName = 2;
        OrderDetail orderDetail = (OrderDetail) orderDetailDao.get(testId);
        orderDetail.setQuantity(newName);
        orderDetailDao.update(orderDetail);
        assertEquals(newName, (Integer) orderDetail.getQuantity());
    }

    private OrderDetail entity;
    protected OrderDetailPK testId;

    @Before
    public void setUp(){
        entity = getEntity();
        testId = ((OrderDetail)orderDetailDao.getAll().get(0)).getOrderDetailPK();
    }

    @Test
    public void testAddAndGetAll() {
        int size = orderDetailDao.getAll().size();
        orderDetailDao.add(entity);
        System.out.println(entity.getDress());
        assertTrue(size < orderDetailDao.getAll().size());
    }

    @Test
    public void testGet() {
        entity = orderDetailDao.get(testId);
        assertNotNull(entity);
    }


    @Test
    public void testDelete() {
        orderDetailDao.delete(testId);
        assertNull(orderDetailDao.get(testId));
    }


    protected OrderDetail getEntity() {
        Dress dress = dressDao.getAll().get(0);
        UserOrder userOrder = new UserOrder(
                new Date(System.currentTimeMillis()),
                orderStatusDao.getAll().get(0),
                userDao.getAll().get(0),
                new HashSet<>(orderDetailDao.getAll()));
        userOrderDao.add(userOrder);
        OrderDetail orderDetail = new OrderDetail(new OrderDetailPK(
                userOrder.getId(),
                dress.getId(),
                dress.getColorSet().iterator().next().getId(),
                dress.getSizeSet().iterator().next().getId()), 2);
        return orderDetail;
    }
}
