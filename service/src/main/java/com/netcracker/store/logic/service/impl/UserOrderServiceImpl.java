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
import com.netcracker.store.persistence.entity.User;
import com.netcracker.store.persistence.entity.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by A-one on 07.05.2017.
 */
@Service
@Transactional
public class UserOrderServiceImpl implements UserOrderService {

    private final UserOrderDao userOrderDao;
    private final UserService userService;
    private final OrderStatusService orderStatusService;
    private final OrderDetailDao orderDetailDao;

    @Autowired
    public UserOrderServiceImpl(UserOrderDao userOrderDao,
                                UserService userService,
                                OrderStatusService orderStatusService,
                                OrderDetailDao orderDetailDao) {
        this.userOrderDao = userOrderDao;
        this.userService = userService;
        this.orderStatusService = orderStatusService;;
        this.orderDetailDao = orderDetailDao;
    }

    @Override
    public void addUserOrder() {
        UserOrder userOrder = new UserOrder();
        userOrder.setDateCreated(new Date(System.currentTimeMillis()));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        userOrder.setUser(userService.getUserByEmail(currentUserName));
        userOrder.setOrderStatus(orderStatusService.getAllOrderStatuses().get(2));
        userOrderDao.add(userOrder);
        Set<OrderDetail> userBag = getUserBag();
        Set<OrderDetail> newOrderDetailSet = new HashSet<>();
        int userOrderId = userOrder.getId();
        for (OrderDetail orderDetail : userBag) {
            newOrderDetailSet.add(new OrderDetail(
                    new OrderDetailPK(userOrderId, orderDetail.getDress().getId()), orderDetail.getQuantity()
            ));
        }
        orderDetailDao.addAll(newOrderDetailSet);
        orderDetailDao.deleteAll(userBag);
    }

    @Override
    public void addToUserBag(OrderDetail orderDetail) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(authentication.getName());
        UserOrder userOrder = userOrderDao.getUserBagOrder(user);
        if (userOrder == null) {
            userOrder = new UserOrder();
            userOrder.setDateCreated(new Date(System.currentTimeMillis()));
            userOrder.setOrderStatus(orderStatusService.getUserBagOrderStatus());
            userOrder.setUser(user);
            userOrderDao.add(userOrder);
        }
        orderDetail.setOrderDetailPK(new OrderDetailPK(userOrder.getId(), orderDetail.getDress().getId()));
        orderDetailDao.add(orderDetail);
    }

    @Override
    public Set<OrderDetail> getUserBag() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserOrder userOrder = userOrderDao.getUserBagOrder(
                userService.getUserByEmail(authentication.getName())
        );
        return userOrder.getOrderDetailSet();
    }

    @Override
    public void deleteDressFromUserBag(OrderDetail orderDetail) {
        orderDetailDao.delete(orderDetail.getOrderDetailPK());
    }
}
