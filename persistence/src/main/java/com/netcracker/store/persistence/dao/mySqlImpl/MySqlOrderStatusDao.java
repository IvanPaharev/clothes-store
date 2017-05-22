package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.dao.OrderStatusDao;
import com.netcracker.store.persistence.entity.OrderStatus;
import org.springframework.stereotype.Component;

import javax.persistence.Query;


/**
 * Created by A-one on 10.04.2017.
 */
@Component
public class MySqlOrderStatusDao extends MySqlBaseDao<OrderStatus, Integer> implements OrderStatusDao {
    public MySqlOrderStatusDao() {
        super(OrderStatus.class);
    }

    @Override
    public OrderStatus getUserBagStatus() {
        Query query = entityManager.createQuery("from OrderStatus where status = :status");
        query.setParameter("status", "IN_USER_BAG");
        return (OrderStatus) query.getResultList().get(0);
    }
}
