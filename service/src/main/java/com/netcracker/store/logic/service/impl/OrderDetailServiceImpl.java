package com.netcracker.store.logic.service.impl;

import com.netcracker.store.logic.service.OrderDetailService;
import com.netcracker.store.persistence.dao.OrderDetailDao;
import com.netcracker.store.persistence.entity.OrderDetail;
import com.netcracker.store.persistence.entity.OrderDetailPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by A-one on 07.05.2017.
 */
@Service
@Transactional
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail, OrderDetailPK> implements OrderDetailService {
    private final OrderDetailDao orderDetailDao;

    public OrderDetailServiceImpl(OrderDetailDao orderDetailDao) {
        super(orderDetailDao);
        this.orderDetailDao = orderDetailDao;
    }
}
