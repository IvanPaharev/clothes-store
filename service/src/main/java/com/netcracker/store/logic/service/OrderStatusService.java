package com.netcracker.store.logic.service;

import com.netcracker.store.persistence.entity.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by A-one on 07.05.2017.
 */
@Service
public interface OrderStatusService extends BaseService<OrderStatus, Integer> {
    List<OrderStatus> getAllOrderStatuses();
    OrderStatus getUserBagOrderStatus();
}
