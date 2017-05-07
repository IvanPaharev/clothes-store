package com.netcracker.store.logic.service.impl;

import com.netcracker.store.logic.dto.DressAndQuantity;
import com.netcracker.store.logic.service.DressService;
import com.netcracker.store.logic.service.OrderStatusService;
import com.netcracker.store.logic.service.UserOrderService;
import com.netcracker.store.logic.service.UserService;
import com.netcracker.store.persistence.dao.OrderDetailDao;
import com.netcracker.store.persistence.dao.UserOrderDao;
import com.netcracker.store.persistence.entity.OrderDetail;
import com.netcracker.store.persistence.entity.OrderDetailPK;
import com.netcracker.store.persistence.entity.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by A-one on 07.05.2017.
 */
@Service
@Transactional
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    private UserOrderDao userOrderDao;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private DressService dressService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    public void addUserOrder() {
        UserOrder userOrder = new UserOrder();
        userOrder.setDateCreated(new Date(System.currentTimeMillis()));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        userOrder.setUser(userService.getUserByEmail(currentUserName));
        userOrder.setOrderStatus(orderStatusService.getAllOrderStatuses().get(2));
        userOrderDao.add(userOrder);
        Set<OrderDetail> orderDetails = new HashSet<>();
        int userOrderId = userOrder.getId();
        for (DressAndQuantity dressAndQuantity : dressService.getUserBag()) {
            orderDetailDao.add(new OrderDetail(
                    new OrderDetailPK(userOrderId, dressAndQuantity.getDress().getId()), dressAndQuantity.getQuantity()));
        }
    }
}
