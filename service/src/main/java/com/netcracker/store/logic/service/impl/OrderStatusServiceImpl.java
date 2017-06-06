package com.netcracker.store.logic.service.impl;

import com.netcracker.store.logic.service.BaseService;
import com.netcracker.store.logic.service.OrderStatusService;
import com.netcracker.store.persistence.dao.OrderStatusDao;
import com.netcracker.store.persistence.entity.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by A-one on 07.05.2017.
 */
@Service
@Transactional
public class OrderStatusServiceImpl extends BaseServiceImpl<OrderStatus, Integer> implements OrderStatusService {
    private final OrderStatusDao orderStatusDao;

    public OrderStatusServiceImpl(OrderStatusDao orderStatusDao) {
        super(orderStatusDao);
        this.orderStatusDao = orderStatusDao;
    }

    @Override
    public List<OrderStatus> getAllOrderStatuses() {
        return orderStatusDao.getAll();
    }

    @Override
    public OrderStatus getUserBagOrderStatus() {
        return orderStatusDao.getUserBagStatus();
    }
}
