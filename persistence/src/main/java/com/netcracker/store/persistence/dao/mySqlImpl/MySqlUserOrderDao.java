package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.entity.User;
import com.netcracker.store.persistence.entity.UserOrder;
import com.netcracker.store.persistence.dao.UserOrderDao;
import org.hibernate.Hibernate;
//import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


/**
 * Created by A-one on 10.04.2017.
 */
@Repository
public class MySqlUserOrderDao extends MySqlBaseDao<UserOrder, Integer> implements UserOrderDao {
    public MySqlUserOrderDao() {
        super(UserOrder.class);
    }

    @Override
    public UserOrder getUserBagOrder(User user) {
        CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<UserOrder> criteriaQuery = builder.createQuery(UserOrder.class);
        Root<UserOrder> root = criteriaQuery.from(UserOrder.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.and(
                builder.equal(root.get("orderStatus").get("status"), "IN_USER_BAG"),
                builder.equal(root.get("user"), user)));
        List<UserOrder> userOrders = entityManager.createQuery(criteriaQuery).getResultList();
        UserOrder userOrder = null;
        if(!userOrders.isEmpty()) {
            userOrder = userOrders.get(0);
            Hibernate.initialize(userOrder.getOrderDetailSet());
        }
        return userOrder;
    }
}
