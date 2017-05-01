package com.netcracker.store.persistence.test;

import com.netcracker.store.persistence.dao.BaseDao;
import com.netcracker.store.persistence.entity.BaseEntity;
import com.netcracker.store.persistence.entity.Dress;
import com.netcracker.store.persistence.entity.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

/**
 * Created by A-one on 10.04.2017.
 */
public class MySqlOrderStatusDaoTest extends BaseDaoGenericTest{

    @Autowired
    @Resource(name = "mySqlOrderStatusDao")
    private BaseDao<OrderStatus, Integer> orderStatusDao;

    @Autowired
    @Resource(name = "mySqlDressDao")
    private BaseDao<Dress, Integer> dressDao;


    @Override
    public void testUpdate() {
        String newName = "test";
        OrderStatus orderStatus = (OrderStatus) getDao().get(testId);
        orderStatus.setStatus(newName);
        getDao().update(orderStatus);
        assertEquals(newName, orderStatus.getStatus());
    }

    @Override
    protected BaseDao getDao() {
        return orderStatusDao;
    }

    @Override
    protected BaseEntity getEntity() {
        return new OrderStatus("test", "descr");
    }
}
