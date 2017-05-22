package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.entity.User;
import com.netcracker.store.persistence.entity.UserOrder;
import com.netcracker.store.persistence.dao.UserOrderDao;
import org.hibernate.Hibernate;
//import org.hibernate.Query;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;


/**
 * Created by A-one on 10.04.2017.
 */
@Component
public class MySqlUserOrderDao extends MySqlBaseDao<UserOrder, Integer> implements UserOrderDao {
    public MySqlUserOrderDao() {
        super(UserOrder.class);
    }

    @Override
    public UserOrder getUserBagOrder(User user) {
        Query query = entityManager.createQuery("from UserOrder " +
                "where user = :user and orderStatus.status = :orderStatus");
        query.setParameter("user", user);
        query.setParameter("orderStatus", "IN_USER_BAG");
        List<UserOrder> userBagOrder = query.getResultList();
        UserOrder userOrder = null;
        if (userBagOrder.size() == 1) {
            userOrder = userBagOrder.get(0);
            Hibernate.initialize(userOrder.getOrderDetailSet());
        }
        return userOrder;
    }
}
