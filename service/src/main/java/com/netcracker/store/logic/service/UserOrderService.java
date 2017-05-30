package com.netcracker.store.logic.service;

import com.netcracker.store.persistence.entity.OrderDetail;
import com.netcracker.store.persistence.entity.UserOrder;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by A-one on 07.05.2017.
 */
@Service
public interface UserOrderService extends BaseService<UserOrder, Integer> {
    void addUserOrder();
    void addToUserBag(OrderDetail orderDetail);
    void deleteDressFromUserBag(OrderDetail orderDetail);
    Set<OrderDetail> getUserBag();
}
