package com.netcracker.store.persistence.dao;

import com.netcracker.store.persistence.entity.OrderStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by A-one on 19.04.2017.
 */
@Repository
public interface OrderStatusDao extends BaseDao<OrderStatus, Integer> {
    OrderStatus getUserBagStatus();
}
