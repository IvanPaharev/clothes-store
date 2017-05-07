package com.netcracker.store.logic.service.impl;

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
public class OrderStatusServiceImpl implements OrderStatusService {

    @Autowired
    private OrderStatusDao orderStatusDao;

    @Override
    public List<OrderStatus> getAllOrderStatuses() {
        return orderStatusDao.getAll();
    }
}
