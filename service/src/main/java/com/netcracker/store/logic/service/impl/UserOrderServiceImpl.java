package com.netcracker.store.logic.service.impl;

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
public class UserOrderServiceImpl extends BaseServiceImpl<UserOrder, Integer> implements UserOrderService {
    private final UserOrderDao userOrderDao;
    private final UserService userService;
    private final OrderStatusService orderStatusService;
    private final OrderDetailDao orderDetailDao;

    public UserOrderServiceImpl(UserOrderDao userOrderDao,
                                UserService userService,
                                OrderStatusService orderStatusService,
                                OrderDetailDao orderDetailDao) {
        super(userOrderDao);
        this.userOrderDao = userOrderDao;
        this.userService = userService;
        this.orderStatusService = orderStatusService;;
        this.orderDetailDao = orderDetailDao;
    }

    @Override
    public UserOrder addUserOrder() {
        UserOrder userOrder = new UserOrder();
        userOrder.setDateCreated(new Date(System.currentTimeMillis()));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        userOrder.setUser(userService.getUserByEmail(currentUserName));
        userOrder.setOrderStatus(orderStatusService.getAllOrderStatuses().get(2));
        userOrderDao.add(userOrder);
        List<OrderDetail> userBag = getUserBag();
        List<OrderDetail> newOrderDetailSet = new ArrayList<>();
        int userOrderId = userOrder.getId();
        for (OrderDetail orderDetail : userBag) {
            newOrderDetailSet.add(new OrderDetail(
                    new OrderDetailPK(
                            userOrderId,
                            orderDetail.getDress().getId(),
                            orderDetail.getColor().getId(),
                            orderDetail.getSize().getId()
                    ), orderDetail.getQuantity()));
        }
        orderDetailDao.addAll(newOrderDetailSet);
        orderDetailDao.deleteAll(userBag);
        return userOrder;
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
        orderDetail.setOrderDetailPK(new OrderDetailPK(
                userOrder.getId(),
                orderDetail.getDress().getId(),
                orderDetail.getColor().getId(),
                orderDetail.getSize().getId()
        ));
        orderDetailDao.add(orderDetail);
    }

    @Override
    public List<OrderDetail> getUserBag() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserOrder userOrder = userOrderDao.getUserBagOrder(
                userService.getUserByEmail(authentication.getName())
        );
        List<OrderDetail> orderDetails = null;
        if (userOrder != null) {
            orderDetails = userOrder.getOrderDetails();
        }
        return orderDetails;
    }

    @Override
    public void deleteDressFromUserBag(OrderDetailPK orderDetailPK) {
        orderDetailDao.delete(orderDetailPK);
    }
}
