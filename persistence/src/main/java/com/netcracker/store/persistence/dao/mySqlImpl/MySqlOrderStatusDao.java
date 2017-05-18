package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.dao.OrderStatusDao;
import com.netcracker.store.persistence.entity.OrderStatus;
import org.hibernate.Query;
import org.springframework.stereotype.Component;


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
        Query query = getCurrentSession().createQuery("from OrderStatus where status = :status");
        query.setParameter("status", "IN_USER_BAG");
        return (OrderStatus) query.list().get(0);
    }
}
