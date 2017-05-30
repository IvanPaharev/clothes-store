package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.dao.OrderStatusDao;
import com.netcracker.store.persistence.entity.OrderStatus;
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
public class MySqlOrderStatusDao extends MySqlBaseDao<OrderStatus, Integer> implements OrderStatusDao {
    public MySqlOrderStatusDao() {
        super(OrderStatus.class);
    }

    @Override
    public OrderStatus getUserBagStatus() {
        CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<OrderStatus> criteriaQuery = builder.createQuery(OrderStatus.class);
        Root<OrderStatus> root = criteriaQuery.from(OrderStatus.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("status"), "IN_USER_BAG"));
        List<OrderStatus> orderStatuses = entityManager.createQuery(criteriaQuery).getResultList();
        OrderStatus orderStatus = null;
        if(!orderStatuses.isEmpty()) {
            orderStatus = orderStatuses.get(0);
        }
        return orderStatus;
    }
}
